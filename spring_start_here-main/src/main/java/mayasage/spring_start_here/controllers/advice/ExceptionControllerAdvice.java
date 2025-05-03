package mayasage.spring_start_here.controllers.advice;

import mayasage.spring_start_here.exceptions.NotEnoughMoneyException;
import mayasage.spring_start_here.models.ErrorDetails;
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
