package com.catalin.javapersistence.models.city_assignment;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "regions")
@Getter
@Setter
public class Region extends AbstractEntityBase {

        private String country;

        private String city;

        private String district;

        private String state;

        @Override
        public String toString() {
                return "Region{" +
                       "country='" + country + '\'' +
                       ", city='" + city + '\'' +
                       ", district='" + district + '\'' +
                       ", state='" + state + '\'' +
                       '}';
        }
}
