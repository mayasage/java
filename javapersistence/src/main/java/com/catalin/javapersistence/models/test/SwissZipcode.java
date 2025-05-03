package com.catalin.javapersistence.models.test;

import lombok.ToString;

@ToString(callSuper = true)
public class SwissZipcode extends Zipcode {
        public SwissZipcode(String value) {
                super(value);
        }
}
