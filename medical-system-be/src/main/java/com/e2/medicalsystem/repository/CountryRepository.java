package com.e2.medicalsystem.repository;

import com.e2.medicalsystem.model.Country;
import com.e2.medicalsystem.model.Location;
import com.e2.medicalsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    public Country findByName(String name);
}
