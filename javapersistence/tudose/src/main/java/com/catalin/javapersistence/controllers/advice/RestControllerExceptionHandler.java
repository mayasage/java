package com.catalin.javapersistence.controllers.advice;

import com.catalin.javapersistence.exceptions.request.InvalidRequestException;
import com.catalin.javapersistence.exceptions.request.RequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

        @ExceptionHandler(InvalidRequestException.class)
        public ResponseEntity<String> invalidRequestExceptionHandler(HttpServletRequest req,
                                                                     InvalidRequestException ex) {

                return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
}
