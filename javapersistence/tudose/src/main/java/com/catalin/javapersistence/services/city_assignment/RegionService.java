package com.catalin.javapersistence.services.city_assignment;

import com.catalin.javapersistence.exceptions.request.InvalidRequestException;
import com.catalin.javapersistence.repositories.city_assignment.RegionRepository;
import com.catalin.javapersistence.repositories.projections.RegionProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

        private final RegionRepository regionRepository;

        public List<String> findRegionsInCascadingOrder(String country, String state, String city) {
                /*

                Cases:

                - country, state, city          fetch districts
                - country, state                fetch cities
                - country                       fetch states
                - nothing                       fetch countries
                - country, city                 not possible
                - state, city                   not possible
                - state                         not possible
                - city                          not possible

                 */

                if (country != null && state != null && city != null) {
                        return regionRepository
                                .findDistinctStateByCountryAndStateAndCity(country, state, city)
                                .stream()
                                .map(RegionProjection.District::getDistrict)
                                .toList();
                }

                if (country != null && state != null && city == null) {
                        return regionRepository
                                .findDistinctStateByCountryAndState(country, state)
                                .stream()
                                .map(RegionProjection.City::getCity)
                                .toList();
                }

                if (country != null && state == null && city == null) {
                        return regionRepository
                                .findDistinctStateByCountry(country)
                                .stream()
                                .map(RegionProjection.State::getState)
                                .toList();
                }

                if (country == null && state == null && city == null) {
                        return regionRepository
                                .findDistinctCountryBy()
                                .stream()
                                .map(RegionProjection.Country::getCountry)
                                .toList();
                }

                throw new InvalidRequestException("Invalid query parameters received");
        }
}
