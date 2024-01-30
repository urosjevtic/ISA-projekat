package com.e2.medicalsystem.model;

import com.e2.medicalsystem.dto.ReservationItemDto;
import jakarta.persistence.*;

@Entity
@Table(name = "reservationItems")
public class ReservationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "count")
    private int count;
    @OneToOne(cascade = CascadeType.ALL)
    private MedicalEquipment equipment;

    public ReservationItem(){}

    public ReservationItem(Long id, int count, MedicalEquipment equipment) {
        this.id = id;
        this.count = count;
        this.equipment = equipment;
    }

    public ReservationItem(ReservationItemDto reservationItemDto){
        this.id = reservationItemDto.getId();
        this.count = reservationItemDto.getCount();
        this.equipment = new MedicalEquipment(reservationItemDto.getEquipment());
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

    public MedicalEquipment getEquipment() {
        return equipment;
    }

    public void setEquipment(MedicalEquipment equipment) {
        this.equipment = equipment;
    }
}
