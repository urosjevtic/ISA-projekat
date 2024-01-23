import { Injectable} from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {BehaviorSubject, Observable} from "rxjs";
import {AuthService} from "../auth/auth.service";
import {TokenStorage} from "../auth/jwt/token.service";
import {Coordinates} from "./model/coordinates.model";


@Injectable({
  providedIn: 'root'
})
export class SimulatorService{
  private stompClient: any;
  private sessionId: any;
  private coordinates : BehaviorSubject<Coordinates> = new BehaviorSubject<Coordinates>(new class implements Coordinates{
    lat: number = 0;
    lng: number = 0;
  });
  constructor(private tokenStorage:TokenStorage) {
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
      this.stompClient.subscribe('/user/queue/message', (message:any) => {
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

}
