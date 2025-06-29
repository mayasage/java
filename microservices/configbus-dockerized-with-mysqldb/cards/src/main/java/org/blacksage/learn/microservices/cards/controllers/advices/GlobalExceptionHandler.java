package org.blacksage.learn.microservices.cards.controllers.advices;

import org.blacksage.learn.microservices.cards.dtos.ResponseDto;
import org.blacksage.learn.microservices.cards.exceptions.CardAlreadyExistsException;
import org.blacksage.learn.microservices.cards.exceptions.CardNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                MethodArgumentNotValidException ex,
                HttpHeaders headers,
                HttpStatusCode status,
                WebRequest request
        ) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ResponseDto.from(
                                "Request validation failed",
                                HttpStatus.BAD_REQUEST.value(),
                                ex.getBindingResult().getAllErrors().stream()
                                        .collect(
                                                HashMap::new,
                                                (map, error) -> {
                                                        String fieldName = ((FieldError) error).getField();
                                                        String errorMessage = error.getDefaultMessage();
                                                        map.put(fieldName, errorMessage);
                                                },
                                                HashMap::putAll
                                        )
                        ));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ResponseDto> handleException(Exception e) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseDto.from(
                                e.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value()
                        ));
        }

        @ExceptionHandler(CardAlreadyExistsException.class)
        public ResponseEntity<ResponseDto> handleCardAlreadyExistsException(CardAlreadyExistsException e) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(ResponseDto.from(
                                e.getMessage(),
                                HttpStatus.CONFLICT.value()
                        ));
        }

        @ExceptionHandler(CardNotFoundException.class)
        public ResponseEntity<ResponseDto> handleCardNotFoundException(CardNotFoundException e) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseDto.from(
                                e.getMessage(),
                                HttpStatus.NOT_FOUND.value()
                        ));
        }
}
