package com.catalin.javapersistence.repositories.projections;

public class RegionProjection {

        public interface Country {
                String getCountry();
        }

        public interface State {
                String getState();
        }

        public interface City {
                String getCity();
        }

        public interface District {
                String getDistrict();
        }
}
