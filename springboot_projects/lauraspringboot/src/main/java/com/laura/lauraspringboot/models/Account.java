package com.laura.lauraspringboot.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class Account {
        @Id
        private int accountId;

        private String accountUniqueIdentifierKey;
        private String accountHolderName;
        private BigDecimal accountBalance;
}
