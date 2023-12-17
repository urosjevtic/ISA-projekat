import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './feature-modules/layout/home/home.component';
import { AllCompaniesComponent } from './feature-modules/layout/all-companies/all-companies.component';
import {ProfileComponent} from "./feature-modules/layout/profile/profile/profile.component";
import { CompanyComponent } from './feature-modules/profiles/company/company.component';
import { RegisterComponent } from './infrastructure/auth/register/register.component';
import { LoginComponent } from './infrastructure/auth/login/login.component';
import { AuthGuard } from './infrastructure/auth/auth.guard';
import { UserReservationsComponent } from './feature-modules/orders/user-reservations/user-reservations.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'company', component: AllCompaniesComponent},
  {path: 'company/:id', component: CompanyComponent },
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'my-reservations', component: UserReservationsComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
