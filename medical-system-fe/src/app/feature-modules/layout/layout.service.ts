import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { RegistrationInfo } from './model/registrationInfo.model';
import { Observable } from 'rxjs';
import { User } from './model/user.model';

@Injectable({
  providedIn: 'root'
})
export class LayoutService {
  private baseUrl = 'http://localhost:8080/api/users';
  constructor(private http: HttpClient) { }

  registerUser(registrationInfo: RegistrationInfo): Observable<User> {
    return this.http.post<User>(this.baseUrl, registrationInfo);
  }
}
