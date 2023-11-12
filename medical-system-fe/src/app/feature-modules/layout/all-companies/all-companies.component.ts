import { Component } from '@angular/core';
import { CompanyProfile } from '../../profiles/model/company.model';
import { LayoutService } from '../layout.service';

@Component({
  selector: 'app-all-companies',
  templateUrl: './all-companies.component.html',
  styleUrls: ['./all-companies.component.scss']
})
export class AllCompaniesComponent {

  companies: CompanyProfile[] = [];
  selectedCompany: CompanyProfile | undefined;
  
  constructor(private layoutService: LayoutService) {}

  ngOnInit(): void {
    this.getAllCompanies();
  }
  
  getAllCompanies() {
    this.layoutService.getAllCompanies().subscribe((data) => {
      this.companies = data;
    });
  }

  getCompanyById(id: number) {
    this.layoutService.getCompanyById(id).subscribe((data) => {
      this.selectedCompany = data;
    });
  }
}
