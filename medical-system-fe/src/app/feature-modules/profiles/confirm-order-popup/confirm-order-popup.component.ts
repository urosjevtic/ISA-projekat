import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MedicalEquipment, Reservation, ReservationItem } from '../model/medical-equipment.model';
import { ProfilesService } from '../profiles.service';
import { Appointment } from '../model/appointment.model';
import {MatSelectModule} from '@angular/material/select';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-confirm-order-popup',
  templateUrl: './confirm-order-popup.component.html',
  styleUrls: ['./confirm-order-popup.component.scss']
})
export class ConfirmOrderPopupComponent {
  company: number = 0;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, 
  private service: ProfilesService, 
  public dialogRef: MatDialogRef<ConfirmOrderPopupComponent>,
  private authService: AuthService,
  private route: ActivatedRoute) { 
    this.reservation = data.reservation || { id: 0, order: [] };
    this.company = data.company;
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
    reserverId: 0
  }
  appointments: Appointment[] = [];
  customAppointments: Appointment[] = [];
  todayDate = new Date();


  reservationForm = new FormGroup({
    appointment: new FormControl('', [Validators.required]),
    date: new FormControl('',[Validators.required]),
    customAppointment: new FormControl('', [Validators.required])
  });
  selectedAppointment: any;
  selectedCustom: any;

  ngOnInit(): void{
    this.service.getAllFreeAppointmentByCompanyId(this.data.company.id).subscribe({
      next: (response) =>{
        this.appointments=response;
        console.log(this.appointments);
      }

    })
  }


  createReservation(){
    var eq: MedicalEquipment[] = [];
    this.reservation.appointment = this.selectedAppointment;
    this.reservation.reserverId = this.getUserId();
  }

  createCustomReservation() {
    this.reservation.appointment = this.selectedCustom;
    this.reservation.reserverId = this.getUserId();
  }

  reserveEquipment():void{
    if(this.reservationForm.get('appointment')?.value === "CUSTOM"){

      this.createCustomReservation();
      this.service.reserveCustom(this.reservation).subscribe({
        next: (response) => {
          alert("Reservation made successfully!");
          this.service.sendReservationQrCode(this.getUserId(),response.id!).subscribe({});
          this.close();
        }
      });

    }else
    {
    console.log(this.selectedAppointment);
    this.createReservation();
    this.service.reserveEquipment(this.reservation!).subscribe({
      next: (response) =>{
        console.log(response);
        this.service.sendReservationQrCode(this.getUserId(), response.id!).subscribe({});
        this.close();
      }
    });

    }
  }

  close(): void{
    this.dialogRef.close();
  }

  private getUserId(): number{
    return this.authService.user$.value.id;
  }

  choseDate(event:any){
    const date = event.value;
    this.service.getFreeCustomAppointments(-2,date).subscribe((result)=>
    {
      this.customAppointments = result;
      console.log(result);
    });
  }


}
