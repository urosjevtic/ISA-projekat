import { Component } from '@angular/core';
import { OrdersService } from '../orders.service';
import { AuthService } from 'src/app/infrastructure/auth/auth.service';
import { Reservation } from '../../profiles/model/medical-equipment.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-reservations',
  templateUrl: './user-reservations.component.html',
  styleUrls: ['./user-reservations.component.scss']
})
export class UserReservationsComponent {

  constructor(private service: OrdersService, private authService: AuthService, private snackBar: MatSnackBar ){}

  reservations: Reservation[] = [];
  userId: any = null;
  

  ngOnInit():void{
    this.userId = this.getUserId();
    this.getAllReservations();
    this.getAllReservationsForAdmins();
  }

  private getAllReservationsForAdmins() {
    this.service.getAllReservations().subscribe({
      next: (response) => {
        this.reservations = response.filter(reservation => !reservation.canceled);
        console.log(this.reservations);
      },
      error: (error) => {
        console.error(error);
      }
    });
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

  isUserCompanyAdmin(): boolean {
    const user = this.authService.user$.value;
    return user.role === 'ROLL_COMPANYADMIN';
  }

  cancelReservation(reservation: any){
    this.service.cancelReservation(reservation.id, this.userId).subscribe({
      next: (response) =>{
        console.log(response);
        this.getAllReservations();
      }
    })
  }

  finishedDelivery(reservation: Reservation) {
    if (!this.isReservationDatePassed(reservation)) {
      reservation.delivered = true;
      this.showSnackbarMessage('Reservation finished by ADMIN!');
      this.service.finishReservation(reservation.id!).subscribe({
        next: (response) => {
          console.log(response);
        }
      })
    } else {
      this.showSnackbarMessage('Cannot finish delivery for past reservations.');
    }
  }

  isReservationDatePassed(reservation: Reservation): boolean {
    const currentDate = new Date();
    const reservationDate = new Date(reservation.appointment.date);
    return reservationDate <= currentDate;
  }

  private showSnackbarMessage(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 5000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom'
    });
  }
}

