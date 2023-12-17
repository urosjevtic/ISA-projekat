import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from '../profiles/model/medical-equipment.model';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {}

  getReservationsByUserId(userId: number): Observable<Reservation[]>{
    return this.http.get<Reservation[]>(this.apiUrl + 'reservation/userReservation?userId=' + userId);
  }

}
