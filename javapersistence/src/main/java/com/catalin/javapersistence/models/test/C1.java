package com.catalin.javapersistence.models.test;

import com.catalin.javapersistence.models.base.AbstractEntityBase;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class C1 extends AbstractEntityBase {

        private String name;

        @Override
        public String toString() {
                return "C1{" +
                       "name='" + name + '\'' +
                       ", id='" + super.getId() +
                       '}';
        }
}
