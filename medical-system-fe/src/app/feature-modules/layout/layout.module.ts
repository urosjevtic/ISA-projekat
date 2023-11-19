import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { HomeComponent } from './home/home.component';
import { AllCompaniesComponent } from './all-companies/all-companies.component';
import { AppRoutingModule } from 'src/app/app-routing.module';
import { ProfileComponent } from './profile/profile/profile.component';
import {MatDividerModule} from "@angular/material/divider";
import { RegisterComponent } from '../../infrastructure/auth/register/register.component';
import { LoginComponent } from '../../infrastructure/auth/login/login.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {FormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    NavbarComponent,
    HomeComponent,
    AllCompaniesComponent,
    RegisterComponent,
    LoginComponent,
    ProfileComponent,
  ],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule,
    MatDividerModule,
    MatAutocompleteModule,
    FormsModule
  ],
  exports: [
    NavbarComponent
  ]
})
export class LayoutModule { }
