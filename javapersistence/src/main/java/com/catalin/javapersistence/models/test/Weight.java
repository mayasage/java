package com.catalin.javapersistence.models.test;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "weight_name")),
        @AttributeOverride(name = "symbol", column = @Column(name = "weight_symbol"))
})
@Getter
@Setter
public class Weight extends Measurement {

        private BigDecimal weightValue;
}
