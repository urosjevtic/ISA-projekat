import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { ConfirmOrderPopupComponent } from '../profiles/confirm-order-popup/confirm-order-popup.component';



@NgModule({
  declarations: [
    ConfirmOrderPopupComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule
  ]
})
export class OrdersModule { }
