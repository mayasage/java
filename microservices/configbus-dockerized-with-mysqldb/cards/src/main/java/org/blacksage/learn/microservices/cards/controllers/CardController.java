package org.blacksage.learn.microservices.cards.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blacksage.learn.microservices.cards.constants.MessageConstants;
import org.blacksage.learn.microservices.cards.dtos.CardDto;
import org.blacksage.learn.microservices.cards.dtos.ContactInfoDto;
import org.blacksage.learn.microservices.cards.dtos.ResponseDto;
import org.blacksage.learn.microservices.cards.services.ICardService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

        private final ICardService iCardService;
        private final ContactInfoDto contactInfoDto;
        private final Environment environment;

        @Value("${build.version}")
        private String buildVersion;

        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createCard(
                @RequestParam String mobileNumber
        ) {
                iCardService.createCard(mobileNumber);

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
                                MessageConstants.CARD_FETCHED_SUCCESSFULLY,
                                HttpStatus.OK.value(),
                                iCardService.getCardByMobileNumber(mobileNumber)
                        )
                );
        }

        @PutMapping("/update")
        public ResponseEntity<ResponseDto> updateCard(
                @Valid @RequestBody CardDto cardDto
        ) {
                iCardService.updateCard(cardDto);

                return ResponseEntity.ok(
                        ResponseDto.from(
                                MessageConstants.CARD_UPDATED_SUCCESSFULLY,
                                HttpStatus.OK.value()
                        )
                );
        }

        @DeleteMapping("/delete")
        public ResponseEntity<ResponseDto> deleteCard(
                @RequestParam String mobileNumber
        ) {
                iCardService.deleteCard(mobileNumber);

                return ResponseEntity.ok(
                        ResponseDto.from(
                                MessageConstants.CARD_DELETED_SUCCESSFULLY,
                                HttpStatus.OK.value()
                        )
                );
        }

        @GetMapping("/java-version")
        public ResponseEntity<String> getJavaVersion() {

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(environment.getProperty("JAVA_HOME"));
        }

        @GetMapping("/build-info")
        public ResponseEntity<String> getBuildVersion() {
                return ResponseEntity.ok(buildVersion);
        }

        @GetMapping("/contact-info")
        public ResponseEntity<ContactInfoDto> getContactInfo() {
                return ResponseEntity.ok(contactInfoDto);
        }
}
