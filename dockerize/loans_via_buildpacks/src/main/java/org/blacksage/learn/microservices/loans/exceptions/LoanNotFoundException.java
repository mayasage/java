package org.blacksage.learn.microservices.loans.exceptions;

public class LoanNotFoundException extends RuntimeException {
        public LoanNotFoundException(String message) {
                super(message);
        }
}
