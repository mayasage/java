package com.catalin.javapersistence.controllers.city_assignment;

import com.catalin.javapersistence.services.city_assignment.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/region")
@RequiredArgsConstructor
public class RegionController {

        private final RegionService regionService;

        @GetMapping({"/", ""})
        public List<String> fetchRegionsInCascadingOrder(
                @RequestParam(required = false) String country,
                @RequestParam(required = false) String state,
                @RequestParam(required = false) String city
        ) {
                if (country != null && country.isEmpty()) country = null;
                if (state != null && state.isEmpty()) state = null;
                if (city != null && city.isEmpty()) city = null;

                return regionService.findRegionsInCascadingOrder(country, state, city);
        }
}
