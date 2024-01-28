import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CompanyProfile } from '../profiles/model/company.model';
import { Contract } from './model/contract.model';
import { webSocket } from 'rxjs/webSocket';
import { Stomp } from '@stomp/stompjs';

@Injectable({
  providedIn: 'root'
})
export class SimulatorService {

  constructor(private http: HttpClient) { 
    this.initializeWebSocketConnection();
  }
  private baseUrl = 'http://localhost:8081/api/';


  sendContract(contract: Contract): Observable<Contract> {
    return this.http.post<Contract>(this.baseUrl + 'producer/send', contract);
  }


  private stompClient: any;
  private messageSubject: Subject<string> = new Subject<string>();
  initializeWebSocketConnection() {
    const socket = new WebSocket('ws://localhost:8081/ws');
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect({}, () => {
        this.stompClient.subscribe('/topic/messages', (message: { body: string; }) => {
            if (message.body) {
                this.messageSubject.next(message.body);
            }
        });
    });
  }

  sendMessage(message: string) {
    this.stompClient.send('/app/send/message', {}, message);
  }

  getMessage(): Observable<string> {
    return this.messageSubject.asObservable();
  }
}
