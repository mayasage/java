package com.laura.lauraspringboot.controllers;

import com.laura.lauraspringboot.models.PaymentDetails;
import com.laura.lauraspringboot.services.PaymentService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Data
public class PaymentController {
        private final PaymentService paymentService;

        @PostMapping("/payments")
        public Mono<PaymentDetails> makePayment(
                @RequestBody PaymentDetails paymentDetails
        ) {
                return paymentService.processPayment(paymentDetails);
        }
}
