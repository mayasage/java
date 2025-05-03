package com.catalin.javapersistence.models.test;

import lombok.ToString;

@ToString(callSuper = true)
public class GermanZipcode extends Zipcode {
        public GermanZipcode(String value) {
                super(value);
        }
}
