import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompanyProfile } from './model/company.model';
import { Observable } from 'rxjs';
import { MedicalEquipment, Reservation } from './model/medical-equipment.model';
import { Appointment } from './model/appointment.model';

@Injectable({
  providedIn: 'root'
})
export class ProfilesService {
  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {}
  
  getCompanyById(id: number): Observable<CompanyProfile> {
    return this.http.get<CompanyProfile>(this.apiUrl + 'company/' + id);
  }

  getAllEquipmentByCompanyId(companyId: number): Observable<MedicalEquipment[]> {
    return this.http.get<MedicalEquipment[]>(this.apiUrl + 'equipment/all?companyId=' + companyId);
  }

  reserveEquipment(reservation: Reservation): Observable<Reservation> {
    return this.http.post<any>(this.apiUrl + 'reservation/' + 'save', reservation);
  }

  updateCompanyProfile(company: CompanyProfile): Observable<any> {
    return this.http.put<any>(this.apiUrl + 'company/' + company.id, company);
  }

  saveAppointment(appointment: Appointment): Observable<any> {
    return this.http.post<any>(this.apiUrl + 'appointment/' + 'save', appointment);
  }

  getAllAppointmentByCompanyId(companyId: number): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.apiUrl + 'appointment/all?companyId=' + companyId);
  }

  deleteEquipmentById(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + 'equipment/' + id);
  }
  
  getAllFreeAppointmentByCompanyId(companyId: number): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.apiUrl + 'appointment/allFree?companyId=' + companyId);
  }

  sendReservationQrCode(senderId: number, reservationId: number):Observable<any>{
    return this.http.get<Appointment[]>(this.apiUrl + 'reservation/generate-and-send-email?senderId='+senderId+'&reservationId='+reservationId);
  }
}
