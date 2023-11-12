import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompanyProfile } from './model/company.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfilesService {
  private apiUrl = 'http://localhost:8080/api/';

  constructor(private http: HttpClient) {}
  
  getCompanyById(id: number): Observable<CompanyProfile> {
    return this.http.get<CompanyProfile>(this.apiUrl + 'company/' + id);
  }
}
