import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserReservationsComponent } from './user-reservations/user-reservations.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import {RouterLink} from "@angular/router";
import { UserContractsComponent } from './user-contracts/user-contracts.component';



@NgModule({
  declarations: [
    UserReservationsComponent,
    UserContractsComponent
  ],
    imports: [
        CommonModule,
        FormsModule,
        MatDialogModule,
        MaterialModule,
        RouterLink,
    ]
})
export class OrdersModule { }
