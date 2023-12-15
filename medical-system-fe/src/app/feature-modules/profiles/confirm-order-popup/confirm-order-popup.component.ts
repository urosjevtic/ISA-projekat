import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserOrder } from '../model/medical-equipment.model';
import { ProfilesService } from '../profiles.service';
import { Appointment } from '../model/appointment.model';
import {MatSelectModule} from '@angular/material/select';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Reservation } from '../model/reservation.model';

@Component({
  selector: 'app-confirm-order-popup',
  templateUrl: './confirm-order-popup.component.html',
  styleUrls: ['./confirm-order-popup.component.scss']
})
export class ConfirmOrderPopupComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private service: ProfilesService) { 
    this.userOrder = data || { id: 0, order: [] };
    console.log(this.userOrder);
  }
  userOrder: UserOrder ={
    id: 0,
    order: []
  }
  appointments: Appointment[] = [];

  reservation: Reservation | undefined;

  reservationForm = new FormGroup({
    appointment: new FormControl('', [Validators.required]),
  });


  ngOnInit(): void{
    this.service.getAllAppointmentByCompanyId(-2).subscribe({
      next: (response) =>{
        this.appointments=response;
        console.log(this.appointments);
      }
      
    })
  }

  createReservation(){
    this.reservation = {
      appointment: {
        id: 0,
        companyId: -2,
        adminId: -3,
        date: new Date(2023, 0, 1, 12, 30),
        duration: 15,
        adminName: 'Pero',
        adminLastName: 'Peric'
      },
      equipment: [{
        id: 0,
        name: '',
        description: '',
        companyId: 0
      }]

    };
  }

  reserveEquipment():void{
    this.createReservation();
    this.service.reserveEquipment(this.reservation!).subscribe({
      next: (response) =>{
        console.log(response);
      }
    })
  }


}
