package org.blacksage.learn.microservices.accounts.controllers.advice;

import org.blacksage.learn.microservices.accounts.dtos.ErrorResponseDto;
import org.blacksage.learn.microservices.accounts.exceptions.CustomerAlreadyExistsException;
import org.blacksage.learn.microservices.accounts.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
                Map<String, String> validationErrors = new HashMap<>();
                List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
                validationErrorList.forEach(error -> {
                        String fieldName = ((FieldError) error).getField();
                        String validationMsg = error.getDefaultMessage();
                        validationErrors.put(fieldName, validationMsg);
                });
                return new ResponseEntity<>(
                        validationErrors,
                        HttpStatus.BAD_REQUEST
                );
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponseDto> handleGlobalException(
                Exception ex,
                WebRequest webRequest) {

                ErrorResponseDto errorResponse = new ErrorResponseDto();
                errorResponse.setApiPath(webRequest.getDescription(false));
                errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
                errorResponse.setErrorMessage(ex.getMessage());
                errorResponse.setErrorTime(LocalDateTime.now());

                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(errorResponse);
        }

        @ExceptionHandler(CustomerAlreadyExistsException.class)
        public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
                CustomerAlreadyExistsException ex,
                WebRequest webRequest) {

                ErrorResponseDto errorResponse = new ErrorResponseDto();
                errorResponse.setApiPath(webRequest.getDescription(false));
                errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
                errorResponse.setErrorMessage(ex.getMessage());
                errorResponse.setErrorTime(LocalDateTime.now());

                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(errorResponse);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
                Exception ex,
                WebRequest webRequest) {

                ErrorResponseDto errorResponse = new ErrorResponseDto();
                errorResponse.setApiPath(webRequest.getDescription(false));
                errorResponse.setErrorCode(HttpStatus.NOT_FOUND);
                errorResponse.setErrorMessage(ex.getMessage());
                errorResponse.setErrorTime(LocalDateTime.now());

                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(errorResponse);
        }
}
