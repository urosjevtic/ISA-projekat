import { Component, OnInit } from '@angular/core';
import { ProfilesService } from '../profiles.service';
import { CompanyProfile } from '../model/company.model';
import { LayoutService } from '../../layout/layout.service';
import { ActivatedRoute } from '@angular/router';
import { Reservation } from '../model/reservation.model';
import { MedicalEquipment } from '../model/medical-equipment.model';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { Appointment } from '../model/appointment.model';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
 
  company: CompanyProfile | undefined;
  medicalEquipments: MedicalEquipment[] = [];
  searchByName: string = '';
  appointmentList: Appointment[] = [];
  reservationDate: string = '';
  isEditFormVisible = false;
  stars: number[] = [1, 2, 3, 4, 5];

  appointment: Appointment = {
    companyId: 0,
    adminId: 0,
    date: new Date(),
    duration: 1,
    adminName: '',
    adminLastName: ''
  };
  
  constructor(private route: ActivatedRoute, private profilesService: ProfilesService, private authService: AuthService) {}

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

  reserveEquipment() {
    if (this.company && this.reservationDate) {
      const reservation: Reservation = {
        companyId: this.company.id,
        date: new Date(this.reservationDate)
      };

      this.profilesService.reserveEquipment(reservation).subscribe((response) => {
        console.log('Rezervacija uspešna!', response);
      });
    }
  }

  updateProfile() {
    if (this.company) {
      this.profilesService.updateCompanyProfile(this.company).subscribe((response) => {
        console.log('Profil ažuriran!', response);
      });
    }
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
        alert('Termin uspešno sačuvan:');
      });
      
    }
  }

  areAllFieldsFilled(): boolean {
    return (
      !!this.appointment.adminName &&
      !!this.appointment.adminLastName &&
      !!this.appointment.date
    );
  }

  filteredMedicalEquipments(): MedicalEquipment[] {
    return this.medicalEquipments.filter(
      equipment => equipment.name.toLowerCase().includes(this.searchByName.toLowerCase())
    );
  }

  deleteMedicalEquipment(id: number): void {
    this.profilesService.deleteEquipmentById(id).subscribe(
      () => {
          alert('Uspešno uklonjeno!');
      },
      (error) => {
        console.error('Failed to delete medical equipment:', error);
      }
    );
  }
}
