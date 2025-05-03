package com.catalin.javapersistence.models.test;

import java.math.BigDecimal;
import java.util.Currency;

public record MonetaryAmount(BigDecimal value, Currency currency) {

        @Override
        public String toString() {
                return value + " " + currency;
        }

        public static MonetaryAmount fromString(String s) {
                String[] split = s.split(" ");
                return new MonetaryAmount(new BigDecimal(split[0]), Currency.getInstance(split[1]));
        }
}
