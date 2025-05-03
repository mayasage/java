package com.catalin.javapersistence.exceptions.services.genid;

public class InvalidSystemClockException extends RuntimeException {
        public InvalidSystemClockException(String message) {
                super(message);
        }
}
