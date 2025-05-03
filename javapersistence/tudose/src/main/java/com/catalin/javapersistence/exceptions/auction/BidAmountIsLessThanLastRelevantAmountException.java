package com.catalin.javapersistence.exceptions.auction;

public class BidAmountIsLessThanLastRelevantAmountException extends AuctionException {
        public BidAmountIsLessThanLastRelevantAmountException(String message) {
                super(message);
        }
}
