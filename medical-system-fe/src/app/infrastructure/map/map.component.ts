import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';
import 'leaflet-routing-machine';
import {SimulatorService} from "./simulator.service";
import {Coordinates} from "./model/coordinates.model";
import {AuthService} from "../auth/auth.service";

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

  constructor(private simulator:SimulatorService,
              private authService:AuthService) {
  }
  ngOnInit() {
    this.initMap();
  }

  private initMap(): void {
    this.map = L.map('map').setView([45.267136, 19.833549], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);

    L.marker([45.267136, 19.833549],{draggable:false}).addTo(this.map);

    L.marker([45.287136, 19.833549],{draggable:false}).addTo(this.map);

    this.setRoute();

    this.initTruck();
    this.updateTruck();
  }

  setRoute(vehicleType: string = 'walking'): void {
    if (this.routingControl) {
      this.map.removeControl(this.routingControl);
    }



    this.routingControl = L.Routing.control({
      waypoints: [
        L.latLng(45.267136, 19.833549),
        L.latLng(45.287136, 19.833549)
      ],
      router: L.routing.mapbox('pk.eyJ1IjoiYm9zaGtvNDIwIiwiYSI6ImNsbno0Y2xnZDEwenQyaXFtbWhoNGw3djEifQ.QgZuryjcj1pb-hGXF0ueRg', {profile: 'mapbox/'+vehicleType})
    }).addTo(this.map);

    this.routingControl.on('routesfound', (event:any) => {
      this.coordinates = event.routes[0].coordinates;
      console.log(this.coordinates);
    })

  }

  initTruck()
  {
    let truckIcon = L.icon({
      iconUrl: '../../../assets/delivery.png',
      iconSize: [48, 48], // Set the size of your custom marker
      iconAnchor: [24, 48], // Adjust the anchor point if needed
      popupAnchor: [0, -48]  // Adjust the popup anchor if you have popups
    });

    this.truck = L.marker([45.267136, 19.833549],{icon:truckIcon}).addTo(this.map);

  }

  updateTruck()
  {
    this.simulator.getCoordinates().subscribe((coordinates:Coordinates) =>{
      if(coordinates.lat != 0) this.truck.setLatLng([coordinates.lat,coordinates.lng]);
    });
  }

  startSimulator()
  {
    this.simulator.startSimulator(this.coordinates,this.authService.user$.getValue().username,"0.5").subscribe((message)=> console.log(message));
  }








}
