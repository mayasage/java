package org.blacksage.learn.microservices.loans.controllers.advice;

import org.blacksage.learn.microservices.loans.dtos.ResponseDto;
import org.blacksage.learn.microservices.loans.exceptions.LoanAlreadyExistsException;
import org.blacksage.learn.microservices.loans.exceptions.LoanNotFoundException;
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
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                MethodArgumentNotValidException ex,
                HttpHeaders headers,
                HttpStatusCode status,
                WebRequest request
        ) {
                Map<String, String> validationErrors =
                        ex.getBindingResult().getAllErrors().stream().
                                collect(
                                        HashMap::new,
                                        (map, error) -> {
                                                String fieldName = ((FieldError) error).getField();
                                                String validationMessage = error.getDefaultMessage();
                                                map.put(fieldName, validationMessage);
                                        },
                                        HashMap::putAll
                                );
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(ResponseDto.from(
                                "Request validation failed",
                                HttpStatus.BAD_REQUEST.value(),
                                validationErrors
                        ));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ResponseDto> handleExceptions(Exception ex) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ResponseDto.from(
                                ex.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR.value()
                        ));
        }

        @ExceptionHandler(LoanAlreadyExistsException.class)
        public ResponseEntity<ResponseDto> handleLoanAlreadyExistsException(
                LoanAlreadyExistsException ex
        ) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(ResponseDto.from(
                                ex.getMessage(),
                                HttpStatus.CONFLICT.value()
                        ));
        }

        @ExceptionHandler(LoanNotFoundException.class)
        public ResponseEntity<ResponseDto> handleLoanNotFoundException(
                LoanNotFoundException ex
        ) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResponseDto.from(
                                ex.getMessage(),
                                HttpStatus.NOT_FOUND.value()
                        ));
        }
}
