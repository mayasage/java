package com.laura.lauraspringboot.proxies;

import com.laura.lauraspringboot.models.PaymentDetails;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Data
public class RestTemplatePaymentsProxy {
        private final RestTemplate restTemplate;

        @Value("${payments.url}")
        private String paymentServiceUrl;

        public PaymentDetails makePayment(String requestId, PaymentDetails paymentDetails) {
                String paymentUrl = paymentServiceUrl + "/payment";
                HttpHeaders headers = new HttpHeaders();
                headers.add("requestId", requestId);
                HttpEntity<PaymentDetails> httpEntity = new HttpEntity<>(paymentDetails, headers);
                return restTemplate.postForObject(paymentUrl, httpEntity, PaymentDetails.class);
        }
}
