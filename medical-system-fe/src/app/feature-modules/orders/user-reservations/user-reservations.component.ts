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
  userId: any = null;

  ngOnInit():void{
    this.userId = this.getUserId();
    this.getAllReservations();
  }

  private getAllReservations(){
    this.service.getReservationsByUserId(this.userId).subscribe({
      next: (response) => {
        console.log(response);
        this.reservations = response;
      }
    })
  }

  
  private getUserId(): number{
    return this.authService.user$.value.id;
  }

  cancelReservation(reservation: any){
    this.service.cancelReservation(reservation.id, this.userId).subscribe({
      next: (response) =>{
        console.log(response);
        this.getAllReservations();
      }
    })


  }
}
