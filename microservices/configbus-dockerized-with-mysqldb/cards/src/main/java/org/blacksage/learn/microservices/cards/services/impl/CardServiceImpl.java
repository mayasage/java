package org.blacksage.learn.microservices.cards.services.impl;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.microservices.cards.constants.CardConstants;
import org.blacksage.learn.microservices.cards.constants.MessageConstants;
import org.blacksage.learn.microservices.cards.dtos.CardDto;
import org.blacksage.learn.microservices.cards.exceptions.CardAlreadyExistsException;
import org.blacksage.learn.microservices.cards.exceptions.CardNotFoundException;
import org.blacksage.learn.microservices.cards.mappers.CardMapper;
import org.blacksage.learn.microservices.cards.models.Card;
import org.blacksage.learn.microservices.cards.repositories.CardRepository;
import org.blacksage.learn.microservices.cards.services.ICardService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

        private final CardRepository cardRepository;

        @Override
        public void createCard(String mobileNumber) {

                if (cardRepository.existsByMobileNumber(mobileNumber)) {
                        throw new CardAlreadyExistsException(MessageConstants.CARD_ALREADY_EXISTS);
                }

                long randomCardNumber = 100000000000L + new Random().nextInt(900000000);

                Card newCard = new Card();
                newCard.setCardNumber(Long.toString(randomCardNumber));
                newCard.setMobileNumber(mobileNumber);
                newCard.setCardType(CardConstants.CREDIT_CARD);
                newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
                newCard.setAmountUsed(0);
                newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);

                cardRepository.save(newCard);
        }

        @Override
        public CardDto getCardByMobileNumber(String mobileNumber) {

                return cardRepository.findByMobileNumber(mobileNumber)
                        .map(CardMapper::toDto)
                        .orElseThrow(() -> new CardNotFoundException(MessageConstants.CARD_NOT_FOUND));
        }

        @Override
        public void updateCard(CardDto cardDto) {

                Card card = cardRepository.findByMobileNumber(cardDto.getMobileNumber())
                        .orElseThrow(() -> new CardNotFoundException(MessageConstants.CARD_NOT_FOUND));

                CardMapper.toModel(cardDto, card);

                cardRepository.save(card);
        }

        @Override
        public void deleteCard(String mobileNumber) {

                Card card = cardRepository.findByMobileNumber(mobileNumber)
                        .orElseThrow(() -> new CardNotFoundException(MessageConstants.CARD_NOT_FOUND));

                cardRepository.deleteById(card.getCardId());
        }
}
