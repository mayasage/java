package org.blacksage.learn.easyschool.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.blacksage.learn.easyschool.rest.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice(basePackages = "org.blacksage.learn.easyschool.rest")
public class RestExceptionController extends ResponseEntityExceptionHandler {

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
                MethodArgumentNotValidException ex,
                HttpHeaders headers,
                HttpStatusCode status,
                WebRequest request
        ) {
                System.out.println("MethodArgumentNotValidException caught in REST controller");

                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setStatusCode(ex.getStatusCode().value());
                apiResponse.setMessage(
                        ex
                                .getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(fieldError ->
                                        fieldError.getField()
                                                + ": "
                                                + fieldError.getDefaultMessage()
                                )
                                .collect(Collectors.joining(", "))
                );
                return ResponseEntity
                        .status(ex.getStatusCode())
                        .body(apiResponse);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse> exceptionHandler(Exception exception) {
                log.error("Exception caught in REST controller: {}", exception.getMessage());

                ApiResponse response = new ApiResponse();
                response.setMessage(exception.getMessage());
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(response);
        }
}
