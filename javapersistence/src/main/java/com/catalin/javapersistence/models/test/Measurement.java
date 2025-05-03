package com.catalin.javapersistence.models.test;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Measurement {

        @NotNull
        @Column(nullable = false)
        private String name;

        @NotNull
        @Column(nullable = false)
        private String symbol;
}
