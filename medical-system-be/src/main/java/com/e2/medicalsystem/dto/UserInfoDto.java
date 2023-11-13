package com.e2.medicalsystem.dto;

public class UserInfoDto {

    public String email;
    public String name;
    public String surname;
    public String city;
    public String country;
    public String phone;
    public String profession;

    public UserInfoDto(String email, String name, String surname, String city, String country, String phone, String profession) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.profession = profession;
    }

    public UserInfoDto() {
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
