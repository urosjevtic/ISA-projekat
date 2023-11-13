import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './feature-modules/layout/home/home.component';
import { AllCompaniesComponent } from './feature-modules/layout/all-companies/all-companies.component';
import { RegisterComponent } from './feature-modules/layout/register/register.component';
import { LoginComponent } from './feature-modules/layout/login/login.component';
import {ProfileComponent} from "./feature-modules/layout/profile/profile/profile.component";

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'all-companies', component: AllCompaniesComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
