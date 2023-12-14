import { Component,OnInit } from '@angular/core';
import {AuthService} from "./infrastructure/auth/auth.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'medical-system-fe';
  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
    this.authService.checkIfUserExists();
  }
}
