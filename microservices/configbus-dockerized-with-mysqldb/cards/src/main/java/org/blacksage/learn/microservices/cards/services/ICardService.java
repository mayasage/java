package org.blacksage.learn.microservices.cards.services;

import org.blacksage.learn.microservices.cards.dtos.CardDto;

public interface ICardService {
        void createCard(String mobileNumber);

        CardDto getCardByMobileNumber(String mobileNumber);

        void updateCard(CardDto cardDto);

        void deleteCard(String mobileNumber);
}
