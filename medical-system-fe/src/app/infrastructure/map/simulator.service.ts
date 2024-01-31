import { Injectable} from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {BehaviorSubject, Observable} from "rxjs";
import {AuthService} from "../auth/auth.service";
import {TokenStorage} from "../auth/jwt/token.service";
import {Coordinates} from "./model/coordinates.model";
import {AuthenticationResponse} from "../auth/model/authentication-response.model";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class SimulatorService{
  private stompClient: any;
  private sessionId: any;
  private baseUrl = 'http://localhost:8080/api/';

  private coordinates : BehaviorSubject<Coordinates> = new BehaviorSubject<Coordinates>(new class implements Coordinates{
    lat: number = 0;
    lng: number = 0;
  });
  constructor(private tokenStorage:TokenStorage,
              private http: HttpClient) {
    this.initWSConnection();
  }

  initWSConnection()
  {

    let jwtToken = this.tokenStorage.getAccessToken();
    let headers = {
      'Authorization': jwtToken
    };

    let socket = new SockJS("http://localhost:8080/ws");
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect(headers, (frame: any) => {
      console.log('WebSocket connection successful:', frame);
      this.stompClient.subscribe('/user/queue/simulator', (message:any) => {
        let newCoordinates : Coordinates = JSON.parse(message.body);
        this.coordinates.next(newCoordinates);
      })


    }, (error: any) => {
      console.error('WebSocket connection error:', error);
    });
  }

  getCoordinates()
  {
    return this.coordinates.asObservable();
  }

  startSimulator(coordinates:any,forUser:string,refreshRate:string)
  {
    return this.http.post<String>(this.baseUrl + `simulator/start?user=${forUser}&refreshRate=${refreshRate}`, coordinates);
  }

  getCompanyCoords(companyId: number)
  {
    return this.http.get<Coordinates>(this.baseUrl + `company/coordinates/${companyId}`);
  }

  markAsReserved(reservationId: number)
  {
    return this.http.get<String>(this.baseUrl + `reservation/finishDelivery/${reservationId}`);
  }

}
