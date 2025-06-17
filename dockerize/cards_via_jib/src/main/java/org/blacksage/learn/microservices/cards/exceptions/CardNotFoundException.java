package org.blacksage.learn.microservices.cards.exceptions;

public class CardNotFoundException extends RuntimeException {
        public CardNotFoundException(String message) {
                super(message);
        }
}
