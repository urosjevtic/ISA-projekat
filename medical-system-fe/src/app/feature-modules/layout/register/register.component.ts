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
    country: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required]),
    profession: new FormControl('', [Validators.required]),

    companyName:new FormControl('', [Validators.required]),
    companyCountry: new FormControl('', [Validators.required]),
    companyCity: new FormControl('', [Validators.required]),
    companyAddress: new FormControl('', [Validators.required]),
    companyWebsite: new FormControl('', [Validators.required])


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
        city: this.registrationForm.value.city || '',
        country: this.registrationForm.value.country || '',
        phone: this.registrationForm.value.phone || '',
        profession: this.registrationForm.value.profession || '',
        companyName: this.registrationForm.value.companyName || '',
        companyCountry: this.registrationForm.value.companyCountry || '',
        companyCity: this.registrationForm.value.companyCity || '',
        companyAddress: this.registrationForm.value.companyAddress || '',
        companyWebsite: this.registrationForm.value.companyWebsite || ''
      }

      this.service.registerUser(registrationInfo).subscribe({
      });
    }else
    {
      this.passwordsMatch = false;
    }

  
  }

}
