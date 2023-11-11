import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './feature-modules/layout/home/home.component';
import { AllCompaniesComponent } from './feature-modules/layout/all-companies/all-companies.component';
import { CompanyComponent } from './feature-modules/profiles/company/company.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'all-companies', component: AllCompaniesComponent},
  {path: 'company', component: CompanyComponent},

  {path: 'all-companies', component: AllCompaniesComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
