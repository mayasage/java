package com.laura.lauraspringboot.controllers;

import com.laura.lauraspringboot.models.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
        @GetMapping("/country/{countryName}")
        public ResponseEntity<Country> france(
                @PathVariable String countryName) {
                Country country = Country.of(
                        countryName.substring(0, 1).toUpperCase() + countryName.substring(1),
                        (int) (Math.random() * 100)
                );
                return ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .header("horse", "Roach")
                        .header("pokemon", "Pickachu")
                        .body(country);
        }

        @GetMapping("/country")
        public List<Country> listAll() {
                return List.of(
                        Country.of("France", (int) (Math.random() * 100)),
                        Country.of("Germany", (int) (Math.random() * 100))
                );
        }
}