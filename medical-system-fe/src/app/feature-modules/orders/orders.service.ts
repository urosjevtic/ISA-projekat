import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from '../profiles/model/medical-equipment.model';
import { Contract } from '../simulators/model/contract.model';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {}

  getAllReservations(): Observable<Reservation[]>{
    return this.http.get<Reservation[]>(this.apiUrl + 'reservation/all');
  }

  getReservationsByUserId(userId: number): Observable<Reservation[]>{
    return this.http.get<Reservation[]>(this.apiUrl + 'reservation/userReservation?userId=' + userId);
  }

  cancelContract(contract: Contract): Observable<Contract> {
    return this.http.post<Contract>(this.apiUrl + 'producer/send', contract);
  }

  cancelReservation(reservationId: number, userId: number): Observable<Reservation>{
    return this.http.delete<Reservation>(this.apiUrl + 'reservation/' + userId + "/" + reservationId)
  }

  getAllContracts(): Observable<any> {
    return this.http.get<any[]>(this.apiUrl + 'contract/all');
  }

  finishReservation(reservationId: number): Observable<any>{
    return this.http.get(this.apiUrl + 'reservation/finishDelivery/' + reservationId);
  }

}
