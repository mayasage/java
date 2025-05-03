package com.catalin.javapersistence.models.test;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class City {

        @NotNull
        @Column(nullable = false, length = 5)
        private Zipcode zipcode;

        @NotNull
        @Column(nullable = false)
        private String name;

        @NotNull
        @Column(nullable = false)
        private String country;

        @Override
        public String toString() {
                return "City{" +
                       "zipcode=" + zipcode +
                       ", name='" + name + '\'' +
                       ", country='" + country + '\'' +
                       '}';
        }

        public static City of(Zipcode zipcode, String name, String country) {
                City city = new City();
                city.setZipcode(zipcode);
                city.setName(name);
                city.setCountry(country);
                return city;
        }
}
