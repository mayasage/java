package com.catalin.javapersistence.models.test;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@RequiredArgsConstructor
@Getter
@ToString
public abstract class Zipcode {
        private final String value;
}
