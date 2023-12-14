import { Component, OnInit } from '@angular/core';
import { ProfilesService } from '../profiles.service';
import { CompanyProfile } from '../model/company.model';
import { LayoutService } from '../../layout/layout.service';
import { ActivatedRoute } from '@angular/router';
import { Reservation } from '../model/reservation.model';
import { MedicalEquipment, Order, UserOrder } from '../model/medical-equipment.model';
import { User } from 'src/app/infrastructure/auth/model/user.model';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { OrderConformationComponent } from '../../orders/order-conformation/order-conformation.component';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
 
  company: CompanyProfile | undefined;
  medicalEquipments: MedicalEquipment[] = [];
  reservationDate: string = '';
  isEditFormVisible = false;
  stars: number[] = [1, 2, 3, 4, 5];
  userOrder: UserOrder ={
    id: 0,
    order: []
  };
  
  constructor(private route: ActivatedRoute, 
    private profilesService: ProfilesService, 
    private authService: AuthService,
    private dialog: MatDialog) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const companyId = params['id'];
      this.getCompanyById(companyId);
      
      this.profilesService.getAllEquipmentByCompanyId(companyId)
      .subscribe(data => this.medicalEquipments = data);
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

  addEquipmentToOrder(equipment: MedicalEquipment) {
    const newOrder: Order = {
      equipment: equipment,
      count: 1
    };
  
    if (this.userOrder && Array.isArray(this.userOrder.order)) {
      const existingOrder = this.userOrder.order.find(order => order.equipment === equipment);
  
      if (existingOrder) {
        existingOrder.count++;
      } else {
        this.userOrder.order.push(newOrder);
      }
  
      console.log(this.userOrder.order);
    } else {
      console.error("Invalid userOrder or order is not an array.");
    }
  }

  finalizeOrder(){
    const dialogRef = this.dialog.open(OrderConformationComponent, {})
  }

}
