import { Component, OnInit } from '@angular/core';
import { ProfilesService } from '../profiles.service';
import { CompanyProfile } from '../model/company.model';
import { LayoutService } from '../../layout/layout.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
 
  company: CompanyProfile | undefined;

  constructor(private route: ActivatedRoute, private layoutService: LayoutService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const companyId = params['id'];
      this.getCompanyById(companyId);
    }); 
  }

  getCompanyById(id: number) {
    this.layoutService.getCompanyById(id).subscribe((data) => {
      this.company = data;
    });
  }
}
