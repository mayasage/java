package org.blacksage.learn.microservices.loans.exceptions;

public class LoanAlreadyExistsException extends RuntimeException {
        public LoanAlreadyExistsException(String message) {
                super(message);
        }
}
