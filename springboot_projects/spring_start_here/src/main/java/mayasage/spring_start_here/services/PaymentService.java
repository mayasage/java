package mayasage.spring_start_here.services;

import mayasage.spring_start_here.models.PaymentDetails;
import mayasage.spring_start_here.proxies.WebClientPaymentsProxy;
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
