import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CompanyProfile} from "../../../profiles/model/company.model";
import {User} from "../../model/user-info.model";
import {NewPasswordInfo} from "../../model/new-password-info.model";


@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private apiUrl = 'http://localhost:8080/api/users/';
  constructor(private http:HttpClient) { }

  getUserInfo(userId: number): Observable<User> {
    return this.http.get<User>(this.apiUrl+ "getUserInfo/" +userId);
  }

  changePassword(newPasswordInfo: NewPasswordInfo,userId: number): Observable<String> {
    return this.http.post(this.apiUrl+ "changePassword/"+userId,newPasswordInfo,{responseType: 'text'});
  }

  changeInfo(userInfo: User,userId: number) : Observable<String>{
    return this.http.post(this.apiUrl+ "changeInfo/"+userId,userInfo,{responseType: 'text'});
  }
}
