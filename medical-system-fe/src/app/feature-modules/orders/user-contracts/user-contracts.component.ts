import { Component, OnInit } from '@angular/core';
import { OrdersService } from '../orders.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { Contract } from '../../simulators/model/contract.model';

@Component({
  selector: 'app-user-contracts',
  templateUrl: './user-contracts.component.html',
  styleUrls: ['./user-contracts.component.scss']
})
export class UserContractsComponent implements OnInit {

  contractList: Contract[] = [];

  contract: Contract = {
    username: '',
    startDate: new Date,
    companyName: '',
    equipment: []
  };

  constructor(private ordersService: OrdersService, private authService: AuthService) {}

  ngOnInit(): void {
    this.ordersService.getAllContracts()
      .subscribe(data => this.contractList = data);
  }

  isUserCompanyAdmin(): boolean {
    const user = this.authService.user$.value;
    return user.role === 'ROLL_COMPANYADMIN';
  }

  cancelContract(contract:Contract){
    console.log(contract);
    contract.contractCanceled = true;
    this.ordersService.cancelContract(contract).subscribe({
      next: (result) => {
        console.log(result);
      }
    });
  }
}
