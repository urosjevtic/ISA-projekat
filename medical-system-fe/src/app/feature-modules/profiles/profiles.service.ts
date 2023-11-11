import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompanyProfile } from './model/company.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfilesService {
  private apiUrl = 'http://localhost:4200';

  constructor(private http: HttpClient) { }

  getAllCompanyProfiles(): Observable<CompanyProfile[]> {
    return this.http.get<CompanyProfile[]>(this.apiUrl + '/company');
  }
}
