import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { HomeComponent } from './home/home.component';
import { AllCompaniesComponent } from './all-companies/all-companies.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { RegisterComponent } from '../../infrastructure/auth/register/register.component';
import { LoginComponent } from '../../infrastructure/auth/login/login.component';



@NgModule({
  declarations: [
    NavbarComponent,
    HomeComponent,
    AllCompaniesComponent,
    RegisterComponent,
    LoginComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule
  ],
  exports: [
    NavbarComponent
  ]
})
export class LayoutModule { }
