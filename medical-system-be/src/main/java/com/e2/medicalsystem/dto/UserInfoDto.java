package com.e2.medicalsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserInfoDto {

    public String email;
    public String username;

    @NotEmpty
    @Size(min = 3, message = "Name should have at least 3 characters")
    public String name;

    @NotEmpty
    @Size(min = 3, message = "Surname should have at least 3 characters")
    public String surname;

    @NotEmpty
    public String city;

    @NotEmpty
    public String country;

    @NotEmpty
    public String address;

    @NotEmpty
    @Size(min = 10, message = "Phone number should have at least 10 characters")
    public String phone;

    @NotEmpty
    public String profession;
    public int penalPoints;

    public UserInfoDto(String email,String username, String name, String surname, String city, String country,String address, String phone, String profession,int penalPoints) {
        this.email = email;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.profession = profession;
        this.address = address;
        this.penalPoints = penalPoints;
    }

    public UserInfoDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
