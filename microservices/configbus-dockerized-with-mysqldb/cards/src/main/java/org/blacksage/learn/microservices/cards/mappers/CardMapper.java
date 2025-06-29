package org.blacksage.learn.microservices.cards.mappers;

import org.blacksage.learn.microservices.cards.dtos.CardDto;
import org.blacksage.learn.microservices.cards.models.Card;

public final class CardMapper {

        private CardMapper() {
        }

        public static CardDto toDto(Card card, CardDto cardDto) {
                cardDto.setMobileNumber(card.getMobileNumber());
                cardDto.setCardNumber(card.getCardNumber());
                cardDto.setCardType(card.getCardType());
                cardDto.setTotalLimit(card.getTotalLimit());
                cardDto.setAmountUsed(card.getAmountUsed());
                cardDto.setAvailableAmount(card.getAvailableAmount());
                return cardDto;
        }

        public static CardDto toDto(Card card) {
                return toDto(card, new CardDto());
        }

        public static Card toModel(CardDto cardDto, Card card) {
                card.setMobileNumber(cardDto.getMobileNumber());
                card.setCardNumber(cardDto.getCardNumber());
                card.setCardType(cardDto.getCardType());
                card.setTotalLimit(cardDto.getTotalLimit());
                card.setAmountUsed(cardDto.getAmountUsed());
                card.setAvailableAmount(cardDto.getAvailableAmount());
                return card;
        }

        public static Card toModel(CardDto cardDto) {
                return toModel(cardDto, new Card());
        }
}
