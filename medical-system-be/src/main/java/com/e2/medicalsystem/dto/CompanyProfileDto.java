package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.CompanyProfile;

public class CompanyProfileDto {
    private long Id;
    private String Name;
    private String Address;
    private double AverageRating;
    //private List<MedicalEquipment> Equipment;

    public CompanyProfileDto(long id, String name, String address, double averageRating) {
        Id = id;
        Name = name;
        Address = address;
        AverageRating = averageRating;
        //Equipment = equipment;
    }

    public CompanyProfileDto(CompanyProfile companyProfile)
    {
        this(companyProfile.getId(), companyProfile.getName(), companyProfile.getAddress(), companyProfile.getAverageRating());
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

    /*public List<MedicalEquipment> getEquipment() {
        return Equipment;
    }

    public void setEquipment(List<MedicalEquipment> equipment) {
        Equipment = equipment;
    }
    */
}
