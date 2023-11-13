import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { RegistrationInfo } from '../model/registrationInfo.model';
import { LayoutService } from '../layout.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  hide = true;
  passwordsMatch: boolean = true;
  constructor(private service: LayoutService){

  }
  registrationForm = new FormGroup({
    email: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
    surname: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    profession: new FormControl(''),

    companyName:new FormControl('', [Validators.required]),
    country: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),


  })


  onRegisterClick()
  {
    if(this.registrationForm.value.password === this.registrationForm.value.confirmPassword)
    {
      this.passwordsMatch = true;
      const registrationInfo: RegistrationInfo={
        email: this.registrationForm.value.email || '',
        password: this.registrationForm.value.password || '',
        confirmPassword: this.registrationForm.value.confirmPassword || '',
        name: this.registrationForm.value.name || '',
        surname: this.registrationForm.value.surname || '',
        phone: this.registrationForm.value.phone || '',
        profession: this.registrationForm.value.profession || '',
        companyName: this.registrationForm.value.companyName || '',
        country: this.registrationForm.value.country || '',
        city: this.registrationForm.value.city || '',
        address: this.registrationForm.value.address || '',
      }

      this.service.registerUser(registrationInfo).subscribe({
      });
    }else
    {
      this.passwordsMatch = false;
    }

  
  }

}
