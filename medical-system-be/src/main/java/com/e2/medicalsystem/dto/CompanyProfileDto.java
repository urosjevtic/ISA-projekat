package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.CompanyProfile;
import com.e2.medicalsystem.model.MedicalEquipment;

import java.util.List;

public class CompanyProfileDto {
    private long Id;
    private String Name;
    private String Address;
    private double AverageRating;
    private String Description;
    private String CompanyLogo;

    public CompanyProfileDto(long id, String name, String address, double averageRating, String description, String companyLogo) {
        Id = id;
        Name = name;
        Address = address;
        AverageRating = averageRating;
        Description = description;
        CompanyLogo = companyLogo;
    }

    public CompanyProfileDto(CompanyProfile companyProfile)
    {
        this(companyProfile.getId(), companyProfile.getName(), companyProfile.getAddress(), companyProfile.getAverageRating(), companyProfile.getDescription(), companyProfile.getCompanyLogo());
    }
    public long getId() { return Id; }

    public void setId(long id) { Id = id; }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getAverageRating() {
        return AverageRating;
    }

    public void setAverageRating(double averageRating) {
        AverageRating = averageRating;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCompanyLogo() {
        return CompanyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        CompanyLogo = companyLogo;
    }

}
