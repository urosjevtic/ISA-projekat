package com.e2.medicalsystem.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    public String username;
    public Date startDate;
    public String companyName;
    @ElementCollection
    public List<String> equipment;

    public Contract() {}

    public Contract(long id, String username, Date startDate, String companyName, List<String> equipment) {
        Id = id;
        this.username = username;
        this.startDate = startDate;
        this.companyName = companyName;
        this.equipment = equipment;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }
}
