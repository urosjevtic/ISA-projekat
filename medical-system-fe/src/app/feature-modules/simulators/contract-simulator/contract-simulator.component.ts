import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import { Contract } from '../model/contract.model';
import { SimulatorService } from '../simulator.service';

@Component({
  selector: 'app-contract-simulator',
  templateUrl: './contract-simulator.component.html',
  styleUrls: ['./contract-simulator.component.scss']
})
export class ContractSimulatorComponent {
  newContract: Contract = {
    username: '',
    startDate: new Date(),
    companyName: '',
    equipment: []
  }
  constructor(private simulatorService: SimulatorService) {

  }
  selectedEquipment: string[] = [];
  selectedCompany: string = '';
  contractForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    startDate: new FormControl('', [Validators.required]),
    companyName: new FormControl('', [Validators.required])
  });

  patchValue(){
    this.newContract.username = this.contractForm.value.username!;
    this.newContract.startDate = new Date(this.contractForm.value.startDate!);
    this.newContract.companyName = this.contractForm.value.companyName!;
    this.selectedCompany = this.contractForm.value.companyName!;
  }

  addEquipment(equipmentName: string){
    this.newContract.equipment.push(equipmentName);
  }

  finishContract(){
    console.log(this.newContract);
    this.simulatorService.sendContract(this.newContract).subscribe({
      next: (result) => {
        console.log(result);
      }
    });
  }
}
