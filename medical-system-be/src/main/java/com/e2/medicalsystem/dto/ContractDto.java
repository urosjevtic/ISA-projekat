package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.Contract;
import com.e2.medicalsystem.model.MedicalEquipment;

import java.util.Date;
import java.util.List;

public class ContractDto {

    public String username;
    public Date startDate;
    public String companyName;
    public List<String> equipment;

    public ContractDto() {}
    public ContractDto(String username, Date startDate, String companyName, List<String> equipment) {
        this.username = username;
        this.startDate = startDate;
        this.companyName = companyName;
        this.equipment = equipment;
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



