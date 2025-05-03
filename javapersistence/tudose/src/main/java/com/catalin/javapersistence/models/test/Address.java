package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import com.catalin.javapersistence.services.genid.SnowflakeSequence;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Embeddable
//@Entity
//@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor

public class Address {

        @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinTable(
                name = "deliveries",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "shipment_id")
        )
        private Set<Shipment> deliveries = new HashSet<>();

        public void newDelivery(Shipment shipment) {
                deliveries.add(shipment);
        }

//        @OneToOne(
//                mappedBy = "shippingAddress",
//                optional = false
//        )
//        private User user;
//
//        @Override
//        public String toString() {
//                return "Address{" +
//                       ", street='" + street + '\'' +
//                       ", zipcode='" + zipcode + '\'' +
//                       ", city='" + city + '\'' +
//                       '}';
//        }

//        @NotNull
//        @Column(nullable = false)
//        private String street;
//
//        @NotNull
//        @AttributeOverride(
//                name = "name",
//                column = @Column(name = "city", nullable = false)
//        )
//        private City city;

//        @Override
//        public String toString() {
//                return "Address{" +
//                       "street='" + street + '\'' +
//                       ", city=" + city +
//                       '}';
//        }

//        @NotNull
        private String street;

//        @NotNull
        private String zipcode;

//        @NotNull
        private String city;

        public Address(String street, String zipcode, String city) {
                this.street = street;
                this.zipcode = zipcode;
                this.city = city;
        }

        @Override
        public String toString() {
                return "Address{" +
                       "deliveries=" + deliveries +
                       ", street='" + street + '\'' +
                       ", zipcode='" + zipcode + '\'' +
                       ", city='" + city + '\'' +
                       '}';
        }
}
