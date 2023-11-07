package com.e2.medicalsystem.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "companies")
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String Name;
    private String Address;
    private double AverageRating;
    //@OneToMany(mappedBy = "id")
    //private List<MedicalEquipment> Equipment;

    public CompanyProfile() {}

    public CompanyProfile(long id, String name, String address, double averageRating) {
        Id = id;
        Name = name;
        Address = address;
        AverageRating = averageRating;
        //Equipment = equipment;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

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

