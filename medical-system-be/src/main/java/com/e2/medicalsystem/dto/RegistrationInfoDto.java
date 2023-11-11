package com.e2.medicalsystem.dto;

public class RegistrationInfoDto {
    public String email;
    public String password;
    public String confirmPassword;
    public String name;
    public String surname;
    public String city;
    public String country;
    public String phone;
    public String profession;

    public String companyName;
    public String companyCountry;
    public String companyCity;
    public String companyAddress;
    public String companyWebsite;

    public RegistrationInfoDto()
    {

    }
    public RegistrationInfoDto(String email, String password, String confirmPassword, String name, String surname, String city, String country, String phone, String profession, String companyName, String companyCountry, String companyCity, String companyAddress, String companyWebsite) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.profession = profession;
        this.companyName = companyName;
        this.companyCountry = companyCountry;
        this.companyCity = companyCity;
        this.companyAddress = companyAddress;
        this.companyWebsite = companyWebsite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    public void setCompanyCountry(String companyCountry) {
        this.companyCountry = companyCountry;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }
}
