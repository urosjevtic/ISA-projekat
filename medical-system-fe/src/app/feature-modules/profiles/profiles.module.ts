import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompanyComponent } from './company/company.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { ConfirmOrderPopupComponent } from './confirm-order-popup/confirm-order-popup.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
@NgModule({
  declarations: [
    CompanyComponent,
    ConfirmOrderPopupComponent

  ],
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MaterialModule
  ]
})
export class ProfilesModule { }
