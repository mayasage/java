package com.catalin.javapersistence.repositories.test;

import com.catalin.javapersistence.models.test.Log;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class LogRepositoryImpl implements LogRepositoryCustom {

        @Lazy
        @Autowired
        private LogRepository logRepository;

        @Override
        @Transactional(value = Transactional.TxType.REQUIRES_NEW)
        public void log(String message) {

                Log log = new Log();
                log.setMessage(message);

                logRepository.save(log);
        }

        @Override
        @Transactional(value = Transactional.TxType.MANDATORY)
        public void logRequireTransaction(String message) {

                Log log = new Log();
                log.setMessage(message);

                logRepository.save(log);
        }

        @Override
        @Transactional(value = Transactional.TxType.NEVER) // cannot be called in a transaction
        public void showLogs() {
                logRepository.findAll().forEach(System.out::println);
        }

        @Override
        @Transactional(value = Transactional.TxType.SUPPORTS)
        public void showLogsSupportsThrowError() {
                Log log = new Log();
                log.setMessage("showLogsSupports called");

                logRepository.save(log);

                logRepository.findAll().forEach(System.out::println);

                throw new RuntimeException("something went wrong");
        }

        @Override
        @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
        public void showLogsNotSupportedThrowError() {
                Log log = new Log();
                log.setMessage("showLogsNotSupported called");

                logRepository.save(log);

                logRepository.findAll().forEach(System.out::println);

                throw new RuntimeException("something went wrong");
        }
}
