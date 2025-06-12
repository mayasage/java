package com.laura.lauraspringboot.proxies;

import com.laura.lauraspringboot.models.PaymentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "payments", url = "${payments.url}")
public interface FeignClientPaymentsProxy {
        @PostMapping("/payment")
        PaymentDetails makePayment(
                @RequestHeader String requestId,
                @RequestBody PaymentDetails paymentDetails
        );
}
