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
    private String Username;
    private Date ContractStartDate;
    private int DeliveryTimeMinutes;
    private String CompanyName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<MedicalEquipment> EquipmentName;

    public Contract() {}

    public Contract(long id, String username, Date contractStartDate, int deliveryTimeMinutes, String companyName, List<MedicalEquipment> equipmentName) {
        Id = id;
        Username = username;
        ContractStartDate = contractStartDate;
        DeliveryTimeMinutes = deliveryTimeMinutes;
        CompanyName = companyName;
        EquipmentName = equipmentName;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Date getContractStartDate() {
        return ContractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        ContractStartDate = contractStartDate;
    }

    public int getDeliveryTimeMinutes() {
        return DeliveryTimeMinutes;
    }

    public void setDeliveryTimeMinutes(int deliveryTimeMinutes) {
        DeliveryTimeMinutes = deliveryTimeMinutes;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public List<MedicalEquipment> getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(List<MedicalEquipment> equipmentName) {
        EquipmentName = equipmentName;
    }
}
