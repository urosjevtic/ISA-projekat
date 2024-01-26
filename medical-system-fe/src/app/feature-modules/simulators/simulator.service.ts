import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CompanyProfile } from '../profiles/model/company.model';
import { Contract } from './model/contract.model';

@Injectable({
  providedIn: 'root'
})
export class SimulatorService {

  constructor(private http: HttpClient) { }
  private baseUrl = 'http://localhost:8081/api/';


  sendContract(contract: Contract): Observable<Contract> {
    return this.http.post<Contract>(this.baseUrl + 'producer/send', contract);
  }
}
