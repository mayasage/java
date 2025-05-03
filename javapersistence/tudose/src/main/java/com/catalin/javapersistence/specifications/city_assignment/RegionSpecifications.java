package com.catalin.javapersistence.specifications.city_assignment;

import com.catalin.javapersistence.models.city_assignment.Region;
import com.catalin.javapersistence.models.city_assignment.Region_;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class RegionSpecifications {

        public static Specification<Region> distinctCountryNames() {
                return (root, query, cb) -> {
                        Predicate predicate = cb.conjunction();
                        root.get(Region_.country);
                        Objects.requireNonNull(query).distinct(true);
                        return predicate;
                };
        }

        public static Specification<Region> distinctStateNames(@NotBlank String countryName) {
                return (root, query, cb) -> {
                        Objects.requireNonNull(query).select(root.get("state")).distinct(true);
                        cb.equal(root.get("country"), countryName);
                        return cb.conjunction();
                };
        }

        public static Specification<Region> filterByCountry(String country) {
                return (root, query, cb) -> cb.equal(root.get("country"), country);
        }

        public static Specification<Region> filterByState(String state) {
                return (root, query, cb) -> cb.equal(root.get("state"), state);
        }

        public static Specification<Region> filterByCity(String city) {
                return (root, query, cb) -> cb.equal(root.get("city"), city);
        }
}
