package com.e2.medicalsystem.service;

import com.e2.medicalsystem.model.City;
import com.e2.medicalsystem.model.Country;

import java.util.List;

public interface CountryService {
    public List<Country> getAllCountries();
    public List<City> getCities(String country);
    public Country getByName(String countryName);
}
