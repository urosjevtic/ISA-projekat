package com.e2.medicalsystem.model;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.sql.Time;
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
    private String Description;
    private String CompanyLogo;
    @OneToMany(fetch = FetchType.EAGER)
    private List<User> CompanyAdmins;

    private Time WrkHrFrom;

    private Time WrkHrTo;

    public CompanyProfile() {}

    public CompanyProfile(long id, String name, String address, double averageRating, String description, String companyLogo, Time wrkHrFrom, Time wrkHrTo) {
        Id = id;
        Name = name;
        Address = address;
        AverageRating = averageRating;
        Description = description;
        CompanyLogo = companyLogo;
        WrkHrFrom = wrkHrFrom;
        WrkHrTo = wrkHrTo;
    }
    public CompanyProfile(List<User> companyAdmins) {
        CompanyAdmins = companyAdmins;
    }


    public Time getWrkHrFrom() {
        return WrkHrFrom;
    }

    public void setWrkHrFrom(Time wrkHrFrom) {
        WrkHrFrom = wrkHrFrom;
    }

    public Time getWrkHrTo() {
        return WrkHrTo;
    }

    public void setWrkHrTo(Time wrkHrTo) {
        WrkHrTo = wrkHrTo;
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

    public List<User> getCompanyAdmins() {
        return CompanyAdmins;
    }

    public void setCompanyAdmins(List<User> companyAdmins) {
        CompanyAdmins = companyAdmins;
    }

}

