package com.e2.medicalsystem.dto;

import com.e2.medicalsystem.model.ReservationItem;

import java.util.List;

public class ReservationItemDto {
    private Long id;
    private int count;
    private MedicalEquipmentDto equipment;


    public ReservationItemDto(){

    }

    public ReservationItemDto(ReservationItem reservationItem){
        this.id = reservationItem.getId();
        this.count = reservationItem.getCount();
        this.equipment = new MedicalEquipmentDto(reservationItem.getEquipment());;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MedicalEquipmentDto getEquipment() {
        return equipment;
    }

    public void setEquipment(MedicalEquipmentDto equipment) {
        this.equipment = equipment;
    }
}
