package com.laura.lauraspringboot.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseDetails {
        private int purchaseId;
        private String productName;
        private BigDecimal productPrice;
}
