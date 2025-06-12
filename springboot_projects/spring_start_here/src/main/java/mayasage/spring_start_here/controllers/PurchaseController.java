package mayasage.spring_start_here.controllers;

import mayasage.spring_start_here.models.PurchaseDetails;
import mayasage.spring_start_here.repositories.PurchaseRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@Data
public class PurchaseController {
        private final PurchaseRepository purchaseRepository;

        @PostMapping
        public void storePurchase(@RequestBody PurchaseDetails purchaseDetails) {
                purchaseRepository.storePurchase(purchaseDetails);
        }

        @GetMapping
        public List<PurchaseDetails> getAllPurchases() {
                return purchaseRepository.findAllPurchases();
        }
}
