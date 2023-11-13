import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompanyProfile } from './model/company.model';
import { Observable } from 'rxjs';
import { Reservation } from './model/reservation.model';

@Injectable({
  providedIn: 'root'
})
export class ProfilesService {
  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {}
  
  getCompanyById(id: number): Observable<CompanyProfile> {
    return this.http.get<CompanyProfile>(this.apiUrl + 'company/' + id);
  }

  reserveEquipment(reservation: Reservation): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'reservation/' + 'save', reservation);
  }
}
