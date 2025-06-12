package com.laura.lauraspringboot.controllers;

import com.laura.lauraspringboot.models.PurchaseDetails;
import com.laura.lauraspringboot.repositories.PurchaseRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

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
