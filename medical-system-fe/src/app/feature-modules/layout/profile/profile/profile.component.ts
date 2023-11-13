import { Component } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent {

  editInfo:boolean = false;
  editPassword: boolean = false;
  profileForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.profileForm = formBuilder.group({
      email: ['',[Validators.required,Validators.email]],
      name: ['',Validators.required],
      surname: ['',Validators.required],
      phone: ['',Validators.required],
      city: ['',Validators.required],
      country: ['',Validators.required],
      profession: ['',Validators.required],
      oldPass: ['',Validators.required],
      newPass: ['',Validators.required],
      confPass: ['',[Validators.required,this.matchPassword('newPass')]]
    });
  }

  private matchPassword(otherControlName: string): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const otherControl = control.root.get(otherControlName);
      if (otherControl && control.value !== otherControl.value) {
        return { mismatch: true };
      }
      return null;
    };
  }
  enableEditInfo() {
    this.editInfo = true;
  }

  disableEditInfo() {
    this.editInfo = false;
  }

  enableEditPassword() {
    this.editPassword = true;
  }

  disableEditPassword() {
    this.editPassword = false;
  }


}
