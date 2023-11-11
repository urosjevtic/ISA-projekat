package com.e2.medicalsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MedicalEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private EquipmentClass equipmentClass;

    public MedicalEquipment()
    {

    }

    public MedicalEquipment(Integer id, String name, String description, EquipmentClass equipmentClass) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.equipmentClass = equipmentClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EquipmentClass getEquipmentClass() {
        return equipmentClass;
    }

    public void setEquipmentClass(EquipmentClass equipmentClass) {
        this.equipmentClass = equipmentClass;
    }

    enum EquipmentClass{
        CLASS1,
        CLASS2,
        CLASS3
    }
}
