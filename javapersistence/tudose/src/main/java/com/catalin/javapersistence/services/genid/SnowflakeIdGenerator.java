package com.catalin.javapersistence.services.genid;

import com.catalin.javapersistence.exceptions.services.genid.InvalidSystemClockException;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class SnowflakeIdGenerator implements IdGenerator {

        private static final Logger logger = Logger.getLogger(SnowflakeIdGenerator.class.getName());

        private static final long epoch = 1742446121284L;

        private static final long workerIdBits = 5L;
        private static final long maxWorkerId = ~(-1L << workerIdBits);

        private static final long datacenterIdBits = 5L;
        private static final long maxDatacenterId = ~(-1L << datacenterIdBits);

        private static final long sequenceBits = 12L;
        private static final long sequenceMask = ~(-1L << sequenceBits);

        private static final long workerIdShift = sequenceBits;
        private static final long datacenterIdShift = sequenceBits + workerIdBits;
        private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
        private final short workerId;
        private final short datacenterId;
        private final AtomicLong lastTimestamp = new AtomicLong(-1L);
        private final AtomicInteger sequence = new AtomicInteger(0);

        public SnowflakeIdGenerator(short workerId, short datacenterId) {
                if (workerId > maxWorkerId || workerId < 0) {
                        logger.severe("invalid worker id: " + workerId);
                        throw new IllegalArgumentException("worker id range is [0, " + maxWorkerId + ")");
                }
                if (datacenterId > maxDatacenterId || datacenterId < 0) {
                        logger.severe("invalid datacenter id: " + datacenterId);
                        throw new IllegalArgumentException("datacenter id range is [0, " + maxDatacenterId + ")");
                }
                this.workerId = workerId;
                this.datacenterId = datacenterId;
        }

        public SnowflakeIdGenerator(short workerId, short datacenterId, short sequence) {
                this(workerId, datacenterId);
                if (sequence > sequenceMask || sequence < 0) {
                        logger.severe("invalid sequence: " + sequence);
                        throw new IllegalArgumentException("sequence range is [0, " + sequenceMask + ")");
                }
                this.sequence.set(sequence);
        }

        @PostConstruct
        public void init() {
                logger.info("worker starting. timestamp left shift: " + timestampLeftShift + ", datacenter id bits: " + datacenterIdBits + ", worker id bits: " + workerIdBits + ", sequence bits: " + sequenceBits + ", datacenterId: " + datacenterId + ", workerId: " + workerId + ", sequence: " + sequence);
        }

        @Override
        public synchronized long nextId() {

                long timestamp = timeGen();

                if (timestamp < lastTimestamp.get()) {
                        logger.severe("clock is moving backwards, rejecting requests until " + lastTimestamp.get());
                        throw new InvalidSystemClockException("clock moved backwards, refusing to generate id");
                }

                if (timestamp == lastTimestamp.get()) {
                        long newVal = sequence.incrementAndGet() & sequenceMask;
                        if (newVal == 0) {
                                sequence.set(0);
                                timestamp = waitTillNextMillisecond(timestamp);
                        }
                } else {
                        sequence.set(0);
                }

                lastTimestamp.set(timestamp);

                return
                        ((timestamp - epoch) << timestampLeftShift) |
                        ((long) datacenterId << datacenterIdShift) |
                        (workerId << workerIdShift) |
                        sequence.get();
        }

        private Long timeGen() {
                return System.currentTimeMillis();
        }

        private long waitTillNextMillisecond(long lastTimestamp) {
                long timestamp = timeGen();
                while (timestamp <= lastTimestamp) {
                        timestamp = timeGen();
                }
                return timestamp;
        }
}
