import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './feature-modules/layout/home/home.component';
import { AllCompaniesComponent } from './feature-modules/layout/all-companies/all-companies.component';
import { CompanyComponent } from './feature-modules/profiles/company/company.component';
import { RegisterComponent } from './feature-modules/layout/register/register.component';
import { LoginComponent } from './feature-modules/layout/login/login.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'company', component: AllCompaniesComponent},
  {path: 'company/:id', component: CompanyComponent },
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
