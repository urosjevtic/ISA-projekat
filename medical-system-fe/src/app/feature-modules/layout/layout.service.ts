import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationInfo } from './model/registrationInfo.model';
import { Observable } from 'rxjs';
import { User } from './model/user-info.model';
import { CompanyProfile } from '../profiles/model/company.model';

@Injectable({
  providedIn: 'root'
})
export class LayoutService {
  private baseUrl = 'http://localhost:8080/api/';
  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) { }

  registerUser(registrationInfo: RegistrationInfo): Observable<User> {
    return this.http.post<User>(this.baseUrl, registrationInfo);
  }

  getAllCompanies(): Observable<CompanyProfile[]> {
    return this.http.get<CompanyProfile[]>(this.apiUrl + 'company/' + 'all');
  }

}
