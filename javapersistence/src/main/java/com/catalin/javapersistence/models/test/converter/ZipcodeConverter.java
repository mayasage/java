package com.catalin.javapersistence.models.test.converter;

import com.catalin.javapersistence.models.test.GermanZipcode;
import com.catalin.javapersistence.models.test.SwissZipcode;
import com.catalin.javapersistence.models.test.Zipcode;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ZipcodeConverter implements AttributeConverter<Zipcode, String> {
        @Override
        public String convertToDatabaseColumn(Zipcode zipcode) {
                return zipcode.getValue();
        }

        @Override
        public Zipcode convertToEntityAttribute(String s) {
                if (s.length() == 5) return new GermanZipcode(s);
                if (s.length() == 4) return new SwissZipcode(s);
                throw new IllegalArgumentException("Unsupported zipcode in database: " + s);
        }
}
