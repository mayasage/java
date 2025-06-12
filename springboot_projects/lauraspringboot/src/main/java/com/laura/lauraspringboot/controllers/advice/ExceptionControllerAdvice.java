package com.laura.lauraspringboot.controllers.advice;

import com.laura.lauraspringboot.exceptions.NotEnoughMoneyException;
import com.laura.lauraspringboot.models.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
        @ExceptionHandler(NotEnoughMoneyException.class)
        public ResponseEntity<ErrorDetails> exceptionNotEnoughMoneyHandler(NotEnoughMoneyException notEnoughMoneyException) {
                ErrorDetails errorDetails = new ErrorDetails();
                errorDetails.setMessage("Not enough money to make the payment.");
                return ResponseEntity.badRequest().body(errorDetails);
        }
}
