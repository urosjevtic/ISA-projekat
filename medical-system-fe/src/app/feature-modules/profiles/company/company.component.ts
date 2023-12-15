import { Component, Inject, OnInit } from '@angular/core';
import { ProfilesService } from '../profiles.service';
import { CompanyProfile } from '../model/company.model';
import { LayoutService } from '../../layout/layout.service';
import { ActivatedRoute } from '@angular/router';
import { MedicalEquipment, Reservation, ReservationItem } from '../model/medical-equipment.model';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { Appointment } from '../model/appointment.model';
import { ConfirmOrderPopupComponent } from '../confirm-order-popup/confirm-order-popup.component';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
 
  company: CompanyProfile | undefined;
  medicalEquipments: MedicalEquipment[] = [];
  appointmentList: Appointment[] = [];
  reservationDate: string = '';
  isEditFormVisible = false;
  stars: number[] = [1, 2, 3, 4, 5];
  reservation: Reservation ={
    id: 0,
    reservationItems: [],
    appointment: {
      companyId: 0,
      adminId: 0,
      date: new Date(),
      duration: 0,
      adminName: '',
      adminLastName: ''
    }
  }
  appointment: Appointment = {
    companyId: 0,
    adminId: 0,
    date: new Date(),
    duration: 0,
    adminName: '',
    adminLastName: ''
  };
  
  constructor(private route: ActivatedRoute, 
    private profilesService: ProfilesService, 
    private authService: AuthService,
    private dialog: MatDialog,
    ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const companyId = params['id'];
      this.getCompanyById(companyId);
      
      this.profilesService.getAllEquipmentByCompanyId(companyId)
      .subscribe(data => this.medicalEquipments = data);
    });  

    this.route.params.subscribe(params => {
      const companyId = params['id'];
      this.getCompanyById(companyId);
      
      this.profilesService.getAllAppointmentByCompanyId(companyId)
      .subscribe(data => this.appointmentList = data);
    });  
  }

  isUserCompanyAdmin(): boolean {
    const user = this.authService.user$.value;
    return user.role === 'ROLL_COMPANYADMIN';
  }

  showEditForm() {
    this.isEditFormVisible = true;
  }

  hideEditForm() {
    this.isEditFormVisible = false;
  }

  getCompanyById(id: number) {
    this.profilesService.getCompanyById(id).subscribe((data) => {
      this.company = data;
    });
  }

  /*reserveEquipment() {
    if (this.company && this.reservationDate) {
      const reservation: Reservation = {
        companyId: this.company.id,
        date: new Date(this.reservationDate)
      };

      this.profilesService.reserveEquipment(reservation).subscribe((response) => {
        console.log('Rezervacija uspešna!', response);
      });
    }
  }*/

  updateProfile() {
    if (this.company) {
      this.profilesService.updateCompanyProfile(this.company).subscribe((response) => {
        console.log('Profil ažuriran!', response);
      });
    }
  }

  addEquipmentToOrder(equipment: MedicalEquipment) {
    const reservationItem: ReservationItem = {
      equipment: equipment,
      count: 1
    };
  
    if (this.reservation && Array.isArray(this.reservation.reservationItems)) {
      const existingOrder = this.reservation.reservationItems.find(order => order.equipment === equipment);
  
      if (existingOrder) {
        existingOrder.count++;
      } else {
        this.reservation.reservationItems.push(reservationItem);
      }
  
      console.log(this.reservation.reservationItems);
    } else {
      console.error("Invalid userOrder or order is not an array.");
    }
  }

  finalizeOrder(){
    const dialogRef = this.dialog.open(ConfirmOrderPopupComponent, {
      data: this.reservation,
    })
  }

  saveAppointment(): void {
    if (this.company && this.appointment) {
      const appointment: Appointment = {
        companyId: this.company.id,
        adminId: this.authService.user$.value.id,
        duration: this.appointment.duration,
        date: this.appointment.date,
        adminName: this.appointment.adminName,
        adminLastName: this.appointment.adminLastName,  
      };
      this.profilesService.saveAppointment(appointment).subscribe(savedAppointment => {
        console.log('Termin uspešno sačuvan:', savedAppointment);
      });
    }
  }
}
