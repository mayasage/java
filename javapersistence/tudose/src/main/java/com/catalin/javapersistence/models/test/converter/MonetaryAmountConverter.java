package com.catalin.javapersistence.models.test.converter;

import com.catalin.javapersistence.models.test.MonetaryAmount;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.Data;

@Data
@Converter
public class MonetaryAmountConverter implements AttributeConverter<MonetaryAmount, String> {
        @Override
        public String convertToDatabaseColumn(MonetaryAmount monetaryAmount) {
                return monetaryAmount.toString();
        }

        @Override
        public MonetaryAmount convertToEntityAttribute(String s) {
                return MonetaryAmount.fromString(s);
        }
}
