import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MedicalEquipment, Reservation, ReservationItem } from '../model/medical-equipment.model';
import { ProfilesService } from '../profiles.service';
import { Appointment } from '../model/appointment.model';
import {MatSelectModule} from '@angular/material/select';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-confirm-order-popup',
  templateUrl: './confirm-order-popup.component.html',
  styleUrls: ['./confirm-order-popup.component.scss']
})
export class ConfirmOrderPopupComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private service: ProfilesService) { 
    this.reservation = data || { id: 0, order: [] };
    console.log(this.reservation);
  }
  reservation: Reservation ={
    appointment: {
      companyId: 0,
      adminId: 0,
      date: new Date(),
      duration: 0,
      adminName: '',
      adminLastName: ''
    },
    reservationItems: [],
  }
  appointments: Appointment[] = [];


  reservationForm = new FormGroup({
    appointment: new FormControl('', [Validators.required]),
  });
  selectedAppointment: any;

  ngOnInit(): void{
    this.service.getAllAppointmentByCompanyId(-2).subscribe({
      next: (response) =>{
        this.appointments=response;
        console.log(this.appointments);
      }
      
    })
  }


  createReservation(){
    var eq: MedicalEquipment[] = [];
    this.reservation.appointment = this.selectedAppointment;
  }

  reserveEquipment():void{
    console.log(this.selectedAppointment);
    this.createReservation();
    this.service.reserveEquipment(this.reservation!).subscribe({
      next: (response) =>{
        console.log(response);
      }
    })
  }


}
