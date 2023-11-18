import { Component } from '@angular/core';
import { CompanyProfile } from '../../profiles/model/company.model';
import { LayoutService } from '../layout.service';
import { Component , OnInit} from '@angular/core';
import { Company } from '../model/company.model';
import {Observable} from "rxjs";

@Component({
  selector: 'app-all-companies',
  templateUrl: './all-companies.component.html',
  styleUrls: ['./all-companies.component.scss']
})
export class AllCompaniesComponent implements OnInit{

  companies: CompanyProfile[] = [];
  
  constructor(private layoutService: LayoutService) {}
  companiesFiltered: Company[] = [];
  searchFilter: string = '';
  minRating: number = 0;

  applyFilters(): void{
    this.companiesFiltered = [];
    this.companies.forEach(company => {
      if((company.name.toLowerCase().includes(this.searchFilter.toLowerCase()) ||
         company.description.toLowerCase().includes(this.searchFilter.toLowerCase()))
      ){
        this.companiesFiltered.push(company)
      }
    });
    console.log(this.companiesFiltered)
  }

  clearFilters(): void{
    this.companiesFiltered = this.companies;
  }

  ngOnInit(): void {
    this.getAllCompanies();
  }
  
  getAllCompanies() {
    this.layoutService.getAllCompanies().subscribe((data) => {
      this.companies = data;
      this.companiesFiltered = this.companies;
    });
  }
}
