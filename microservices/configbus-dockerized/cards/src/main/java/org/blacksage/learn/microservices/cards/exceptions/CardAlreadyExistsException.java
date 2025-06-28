package org.blacksage.learn.microservices.cards.exceptions;

public class CardAlreadyExistsException extends RuntimeException {
        public CardAlreadyExistsException(String message) {
                super(message);
        }
}
