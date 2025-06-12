package com.laura.lauraspringboot.proxies;

import com.laura.lauraspringboot.models.PaymentDetails;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Data
public class WebClientPaymentsProxy {
        private final WebClient webClient;

        @Value("${payments.url}")
        private String paymentServiceUrl;

        public Mono<PaymentDetails> makePayment(String requestId, PaymentDetails paymentDetails) {
                return webClient
                        .post()
                        .uri(paymentServiceUrl + "/payment")
                        .header("requestId", requestId)
                        .body(Mono.just(paymentDetails), PaymentDetails.class)
                        .retrieve()
                        .bodyToMono(PaymentDetails.class);
        }
}
