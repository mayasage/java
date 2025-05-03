package com.catalin.javapersistence.models.test;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filename {

        @NotNull
        @Column(nullable = false)
        private String name;

        @Override
        public String toString() {
                return "Filename{" +
                       "name='" + name + '\'' +
                       '}';
        }

        @Override
        public boolean equals(Object o) {
                if (o == null || getClass() != o.getClass()) return false;
                Filename filename = (Filename) o;
                return Objects.equals(name, filename.name);
        }

        @Override
        public int hashCode() {
                return Objects.hashCode(name);
        }
}
