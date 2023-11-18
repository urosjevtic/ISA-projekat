package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.Country;

public class CountryDto {
    public Integer id;
    public String name;

   public CountryDto(){}

    public CountryDto(Country country)
    {
        this.id = country.getId();
        this.name = country.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
