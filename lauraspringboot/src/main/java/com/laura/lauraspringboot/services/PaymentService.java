package com.laura.lauraspringboot.services;

import com.laura.lauraspringboot.models.PaymentDetails;
import com.laura.lauraspringboot.proxies.WebClientPaymentsProxy;
import lombok.Data;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Data
public class PaymentService {
        private final WebClientPaymentsProxy webClientPaymentProxy;

        public Mono<PaymentDetails> processPayment(PaymentDetails paymentDetails) {
                String requestId = UUID.randomUUID().toString();
                return webClientPaymentProxy.makePayment(requestId, paymentDetails);
        }
}
