package com.catalin.javapersistence.repositories.city_assignment;

import com.catalin.javapersistence.models.city_assignment.Region;
import com.catalin.javapersistence.repositories.projections.RegionProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long>, JpaSpecificationExecutor<Region> {

        List<RegionProjection.Country> findDistinctCountryBy();

        List<RegionProjection.State> findDistinctStateByCountry(String country);

        List<RegionProjection.City> findDistinctStateByCountryAndState(String country, String state);

        List<RegionProjection.District> findDistinctStateByCountryAndStateAndCity(String country,
                                                                                  String state,
                                                                                  String city);
}