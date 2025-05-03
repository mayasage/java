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
        @AttributeOverride(name = "name", column = @Column(name = "dimension_name")),
        @AttributeOverride(name = "symbol", column = @Column(name = "dimension_symbol"))
})
@Getter
@Setter
public class Dimensions extends Measurement {

        private BigDecimal depth;

        private BigDecimal height;

        private BigDecimal width;
}
