package mayasage.spring_start_here.models;

import lombok.Data;

@Data
public class Country {
        private String name;
        private int population;

        public static Country of(String name, int population) {
                Country country = new Country();
                country.name = name;
                country.population = population;
                return country;
        }
}
