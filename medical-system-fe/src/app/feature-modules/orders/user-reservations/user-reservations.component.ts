import { Component } from '@angular/core';
import { OrdersService } from '../orders.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { Reservation } from '../../profiles/model/medical-equipment.model';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.scss']
})
export class UserReservationsComponent {
  
  constructor(private service: OrdersService, private authService: AuthService){}

  reservations: Reservation[] = [];

  ngOnInit():void{
    const userId = this.getUserId();
    this.service.getReservationsByUserId(userId).subscribe({
      next: (response) => {
        console.log(response);
        this.reservations = response;
      }
    })
  }


  
  private getUserId(): number{
    return this.authService.user$.value.id;
  }
}
