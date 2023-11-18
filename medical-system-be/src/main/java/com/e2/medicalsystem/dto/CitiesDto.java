package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.City;
import com.e2.medicalsystem.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CitiesDto {
    public String country;
    public List<String> cities;


    public CitiesDto(){}
    public CitiesDto(Country country){
        this.country = country.getName();
        List<String> allCities = new ArrayList<>();
        for(City city : country.getCities())
        {
            allCities.add(city.getName());
        }
        this.cities = allCities;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
