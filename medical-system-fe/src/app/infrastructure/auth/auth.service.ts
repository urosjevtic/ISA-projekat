import { Injectable } from '@angular/core';
import { RegistrationInfo } from './model/registrationInfo.model';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Login } from './model/login.model';
import { AuthenticationResponse } from './model/authentication-response.model';
import { TokenStorage } from './jwt/token.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { User } from './model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/';
  user$ = new BehaviorSubject<User>({username: "", role: "" });
  constructor(private http: HttpClient, private tokenStorage: TokenStorage) { }


  registerUser(registrationInfo: RegistrationInfo): Observable<User> {
    return this.http.post<User>(this.baseUrl+'auth/register', registrationInfo);
  }

  loginUser(login: Login): Observable<AuthenticationResponse> {
    return this.http
    .post<AuthenticationResponse>(this.baseUrl+'auth/login', login)
    .pipe(
      tap((authenticationResponse) => {
      this.tokenStorage.saveAccessToken(authenticationResponse.accessToken);
      this.setUser();
    })
    );
  }

  getUsers(): Observable<User[]>{
    return this.http.get<User[]>(this.baseUrl+'users/all');
  }

  private setUser(): void{
    const jwtHelperService = new JwtHelperService();
    const accessToken = this.tokenStorage.getAccessToken() || "";
    const user: User = {
      username: jwtHelperService.decodeToken(accessToken).username,
      role: jwtHelperService.decodeToken(accessToken).role
    };
    this.user$.next(user);
  }

}
