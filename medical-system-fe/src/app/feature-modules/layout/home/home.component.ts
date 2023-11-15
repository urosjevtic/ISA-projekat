import { Component } from '@angular/core';
import { CompanyProfile } from '../../profiles/model/company.model';
import { LayoutService } from '../layout.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  currentIndex: number = 0;
  companies: CompanyProfile[] = [];

  constructor(private layoutService: LayoutService) {}

  ngOnInit(): void {
    this.getAllCompanies();
  }

  getAllCompanies() {
    this.layoutService.getAllCompanies().subscribe((data) => {
      this.companies = data;
    });
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
