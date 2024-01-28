import { NgModule } from '@angular/core';
import {CommonModule, DatePipe} from '@angular/common';
import { CompanyComponent } from './company/company.component';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { ConfirmOrderPopupComponent } from './confirm-order-popup/confirm-order-popup.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
@NgModule({
  declarations: [
    CompanyComponent,
    ConfirmOrderPopupComponent

  ],
    imports: [
        CommonModule,
        FormsModule,
        MatDialogModule,
        MaterialModule,
        MatDatepickerModule,
      MatFormFieldModule
    ]
})
export class ProfilesModule { }
