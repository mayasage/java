package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class I1 extends AbstractEntityBase {

        @ManyToOne
        private C1 category;

        private String name;
        private BigDecimal buyNowPrice;

//        @Override
//        public String toString() {
//                return "I1{" +
//                       "category=" + category +
//                       ", id=" + super.getId() +
//                       ", version=" + super.getVersion() +
//                       ", name='" + name + '\'' +
//                       ", buyNowPrice=" + buyNowPrice +
//                       '}';
//        }

        @Override
        public String toString() {
                return "hello";
        }
}
