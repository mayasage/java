package org.blacksage.learn.microservices.cards.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blacksage.learn.microservices.cards.constants.MessageConstants;
import org.blacksage.learn.microservices.cards.dtos.CardDto;
import org.blacksage.learn.microservices.cards.dtos.ResponseDto;
import org.blacksage.learn.microservices.cards.services.ICardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CardController {

        private final ICardService cardService;

        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createCard(
                @RequestParam String mobileNumber
        ) {
                cardService.createCard(mobileNumber);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(ResponseDto.from(
                                MessageConstants.CARD_CREATED_SUCCESSFULLY,
                                HttpStatus.CREATED.value())
                        );
        }

        @GetMapping("/fetch")
        public ResponseEntity<ResponseDto> getCard(
                @RequestParam String mobileNumber
        ) {
                return ResponseEntity.ok(
                        ResponseDto.from(
                                "Card fetched successfully",
                                HttpStatus.OK.value(),
                                cardService.getCardByMobileNumber(mobileNumber)
                        )
                );
        }

        @PutMapping("/update")
        public ResponseEntity<ResponseDto> updateCard(
                @Valid @RequestBody CardDto cardDto
        ) {
                cardService.updateCard(cardDto);
                return ResponseEntity.ok(
                        ResponseDto.from(
                                "Card updated successfully",
                                HttpStatus.OK.value()
                        )
                );
        }
}
