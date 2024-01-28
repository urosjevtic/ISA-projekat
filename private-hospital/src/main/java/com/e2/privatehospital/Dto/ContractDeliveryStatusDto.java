package com.e2.privatehospital.Dto;

import java.util.List;

public class ContractDeliveryStatusDto {
    public String username;
    public String company;
    public List<String> equipment;
    public EContractStatus status;

    public ContractDeliveryStatusDto(){}
    public ContractDeliveryStatusDto(String username, String company, List<String> equipment, EContractStatus status) {
        this.username = username;
        this.company = company;
        this.equipment = equipment;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public EContractStatus getStatus() {
        return status;
    }

    public void setStatus(EContractStatus status) {
        this.status = status;
    }
}
