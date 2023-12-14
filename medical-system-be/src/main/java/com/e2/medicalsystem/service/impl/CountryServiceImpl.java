package com.e2.medicalsystem.service.impl;

import com.e2.medicalsystem.model.City;
import com.e2.medicalsystem.model.Country;
import com.e2.medicalsystem.repository.CountryRepository;
import com.e2.medicalsystem.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public List<City> getCities(String countryName) {
        Country country = countryRepository.findByName(countryName);
        return country.getCities();
    }

    @Override
    public Country getByName(String countryName) {
        Country country = countryRepository.findByName(countryName);
        return country;
    }
}
