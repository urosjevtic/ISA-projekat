import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from '../model/login.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  hide = true;

  constructor(private router: Router, private service: AuthService){}

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  onLogin(){
    const loginInfo: Login={
      username: this.loginForm.value.username || '',
      password: this.loginForm.value.password || ''
    };

    if (this.loginForm.valid) {
      this.service.loginUser(loginInfo).subscribe({
        next: () => {
          this.router.navigate(['/home']);
          console.log(this.service.user$)
        },
      });
    }
  }

  navigateToRegister(){
    this.router.navigate(['/register'])
  }
}
