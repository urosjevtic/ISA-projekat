package com.e2.medicalsystem.controller;

import com.e2.medicalsystem.dto.CitiesDto;
import com.e2.medicalsystem.dto.CountryDto;
import com.e2.medicalsystem.dto.UsersDto;
import com.e2.medicalsystem.model.Country;
import com.e2.medicalsystem.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CountryDto>> getAllCountries()
    {
        List<Country> countries = countryService.getAllCountries();
        List<CountryDto> countriesDto = new ArrayList<>();
        for (Country country : countries) {
            countriesDto.add(new CountryDto(country));
        }

        return new ResponseEntity<>(countriesDto, HttpStatus.OK);
    }

    @GetMapping(value = "/city/{countryName}")
    public ResponseEntity<CitiesDto> getCities(@PathVariable String countryName){
        Country country = countryService.getByName(countryName);
        CitiesDto cities = new CitiesDto(country);
        return new ResponseEntity<>(cities, HttpStatus.OK);

    }
}
