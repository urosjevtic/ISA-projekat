import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContractSimulatorComponent } from './contract-simulator/contract-simulator.component';
import { MaterialModule } from 'src/app/infrastructure/material/material.module';
import { FormsModule } from '@angular/forms';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatDividerModule } from '@angular/material/divider';
import { AppRoutingModule } from 'src/app/app-routing.module';
import {MatFormFieldModule} from '@angular/material/form-field';



@NgModule({
  declarations: [
    ContractSimulatorComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule,
    MatDividerModule,
    MatAutocompleteModule,
    FormsModule,
    MatFormFieldModule
  ]
})
export class SimulatorsModule { }
