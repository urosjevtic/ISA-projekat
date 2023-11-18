package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    public Country findByName(String name);
}
