import { Component, OnInit } from '@angular/core';
import { ProfilesService } from '../profiles.service';
import { CompanyProfile } from '../model/company.model';
import { LayoutService } from '../../layout/layout.service';
import { ActivatedRoute } from '@angular/router';
import { Reservation } from '../model/reservation.model';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
 
  company: CompanyProfile | undefined;
  reservationDate: string = '';
  isEditFormVisible = false;
  constructor(private route: ActivatedRoute, private profilesService: ProfilesService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const companyId = params['id'];
      this.getCompanyById(companyId);
    }); 

    
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
}
