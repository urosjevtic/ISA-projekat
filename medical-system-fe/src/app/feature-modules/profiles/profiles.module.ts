import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyComponent } from './company/company.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
@NgModule({
  declarations: [
    CompanyComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule
  ]
})
export class ProfilesModule { }
