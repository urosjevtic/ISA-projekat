import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from "../../../infrastructure/auth/auth.service";
import {User} from "../../../infrastructure/auth/model/user.model";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit{

  user: User | undefined;
  constructor(private router: Router,
              private authService: AuthService){}

  ngOnInit(): void {
    this.authService.user$.subscribe(user => {
      this.user = user;
    });
  }

  onLogout(): void {
    this.authService.logout();
  }

}
