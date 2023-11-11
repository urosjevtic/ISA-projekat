import { Component, OnInit } from '@angular/core';
import { ProfilesService } from '../profiles.service';
import { CompanyProfile } from '../model/company.model';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
 
  companyProfiles: CompanyProfile[] = [];

  constructor(private profilesService: ProfilesService) {}

  ngOnInit(): void {
    this.getAllCompanyProfiles();
  }

  getAllCompanyProfiles() {
    this.profilesService.getAllCompanyProfiles().subscribe((data) => {
      this.companyProfiles = data;
    });
  }

}
