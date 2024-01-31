import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-routing-machine';
import {SimulatorService} from "./simulator.service";
import {Coordinates} from "./model/coordinates.model";
import {AuthService} from "../auth/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {latLng} from "leaflet";


@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.scss']
})
export class MapComponent implements OnInit {
  private map: any;
  private routingControl: any;
  private coordinates: any;
  private truck!: L.Marker;
  public simulatorStarted: boolean = false;
  public refreshRate: number = 0.5;
  private currentPos: L.LatLng = new L.LatLng(45.267136, 19.833549);
  private companyPos: L.LatLng = new L.LatLng(45.287136, 19.833549);
  private reservationId!: number;
  private companyId!: number;

  constructor(private simulator:SimulatorService,
              private authService:AuthService,
              private route: ActivatedRoute,
              private router: Router) {
  }
  ngOnInit() {
    this.initDeliveryId();
  }

  private initDeliveryId(){
    this.route.params.subscribe(params => {
      this.reservationId = params['reservationId'];
      this.companyId = params['companyId'];

      this.initMap();

    });
  }
  private initMap(): void {
    this.map = L.map('map').setView([45.267136, 19.833549], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);

    this.initCompanyCoords();
    this.updateTruck();
  }

  private initCompanyCoords()
  {
    this.simulator.getCompanyCoords(this.companyId).subscribe((coords) => {
        this.companyPos = L.latLng(coords.lat,coords.lng);
        this.initCurrentLocation();
    } );

  }
  private setRoute(vehicleType: string = 'driving'): void {
    if (this.routingControl) {
      this.map.removeControl(this.routingControl);
    }

    const plan = L.routing.plan(
       [this.currentPos,this.companyPos],
      {
        draggableWaypoints: false
      }
    )

    this.routingControl = L.Routing.control({
      plan: plan,
      router: L.routing.mapbox('pk.eyJ1IjoiYm9zaGtvNDIwIiwiYSI6ImNsbno0Y2xnZDEwenQyaXFtbWhoNGw3djEifQ.QgZuryjcj1pb-hGXF0ueRg', {profile: 'mapbox/'+vehicleType})
    }).addTo(this.map);

    this.routingControl.on('routesfound', (event:any) => {
      this.initTruck();
      this.coordinates = event.routes[0].coordinates;
      console.log(this.coordinates);
    })

  }

  private initCurrentLocation(){
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          this.currentPos = L.latLng(position.coords.latitude,position.coords.longitude);
          this.setRoute();
        },
        (error) => {
          this.setRoute();
          console.error(`Error getting location: ${error.message}`);
        }
      );
    } else {
      this.setRoute();
      console.error('Geolocation is not supported by this browser.');
    }
  }
  private initTruck()
  {
    let truckIcon = L.icon({
      iconUrl: '../../../assets/delivery.png',
      iconSize: [48, 48],
      iconAnchor: [24, 48],
      popupAnchor: [0, -48]
    });

    this.truck = L.marker(this.currentPos,{icon:truckIcon}).addTo(this.map);

  }

  private updateTruck()
  {
    this.simulator.getCoordinates().subscribe((coordinates:Coordinates) =>{
      if(coordinates.lat != 0) this.truck.setLatLng([coordinates.lat,coordinates.lng]);
    });
  }

  private finishDelivery(){
    this.simulator.markAsReserved(this.reservationId).subscribe(() =>
      {
        alert("Delivery finished successfully!");
        this.router.navigate(['/my-reservations']);
      },
      error => {});
  }
  startSimulator()
  {
    this.simulatorStarted = true;
    this.simulator.startSimulator(this.coordinates,this.authService.user$.getValue().username,this.refreshRate.toString()).subscribe((message)=>{
      console.log("Simulator ended successfully!");
      this.finishDelivery();
    },
    error => {
      console.log("Error while starting simulator!");
      this.simulatorStarted = false;
    });

  }










}
