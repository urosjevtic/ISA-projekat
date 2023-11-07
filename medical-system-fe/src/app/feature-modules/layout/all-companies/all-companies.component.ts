import { Component } from '@angular/core';
import { Company } from '../model/company.model';

@Component({
  selector: 'app-all-companies',
  templateUrl: './all-companies.component.html',
  styleUrls: ['./all-companies.component.scss']
})
export class AllCompaniesComponent {

  company1: Company = {
    name: 'Kompanija1',
    description: 'Opis kompanije',
    companyLogo: ''
  };
  
  company3: Company = {
    name: 'Kompanija3',
    description: 'Opis trece kompanije',
    companyLogo: ''
  };
  
  company2: Company = {
    name: 'Kompanija2',
    description: 'Opis kompanije asdkja lskdja lskdjalk sjdla jsdklaj lsdkja lsdj kalsjdkl ajdslaj fkasljf sdhf kasdhf kahdsfklash fdkasjldfk hfksdhf ksjdf jksadf',
    companyLogo: ''
  };
  
  company4: Company = {
    name: 'Kompanija4',
    description: 'Opis kompanije asdkja lskdja lskdjalk sjdla jsdklaj lsdkja lsdj kalsjdkl ajdslaj fkasljf sdhf kasdhf kahdsfklash fdkasjldfk hfksdhf ksjdf jksadf',
    companyLogo: ''
  };
  companies: Company[] = [this.company1, this.company2, this.company3, this.company4, this.company4, this.company4]


}
