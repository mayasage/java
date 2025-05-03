package com.blacksage.sequence_generator;

import org.junit.Test;

public class BitManipulationTest {

        @Test
        public void showMeTheBits() {
                Long timestamp = getTimestamp();
                System.out.println("Timestamp: " + timestamp);
        }

        private Long getTimestamp() {
                return System.currentTimeMillis();
        }
}
