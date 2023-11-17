import { Component } from '@angular/core';
import { Company } from '../model/company.model';
import { OnInit } from '@angular/core';
import { LayoutService } from '../layout.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

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

currentIndex: number = 0;
constructor(private service: LayoutService) {}

ngOnInit(){
  this.service.getUsers().subscribe({
    next: (result)=>{
      console.log(result);
    }
  })
}

// Function to go to the previous slide
prevSlide() {
  if (this.currentIndex > 0) {
    this.currentIndex--;
  }
}

// Function to go to the next slide
nextSlide() {
  if (this.currentIndex < this.companies.length - 3) {
    this.currentIndex++;
  }
}
}
