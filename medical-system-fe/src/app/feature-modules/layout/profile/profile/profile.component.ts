import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {ProfileService} from "./profile.service";
import {AuthService} from "../../../../infrastructure/auth/auth.service";
import {NewPasswordInfo} from "../../model/new-password-info.model";
import {User} from "../../model/user-info.model";


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit{

  editInfo:boolean = false;
  editPassword: boolean = false;
  userForm!: FormGroup;
  passwordForm!: FormGroup;

  constructor(private formBuilder: FormBuilder,private profileService:ProfileService,private authService: AuthService) {
    this.initPasswordForm();
    this.initUserForm();
  }

  ngOnInit(): void {

    console.log(this.authService.user$.value)

    this.getUserInfo();
  }

  private initUserForm(): void{
    this.userForm = this.formBuilder.group({
      email: ['',[Validators.required,Validators.email]],
      username: [''],
      name: ['',Validators.required],
      surname: ['',Validators.required],
      phone: ['',Validators.required],
      city: ['',Validators.required],
      address: ['',Validators.required],
      country: ['',Validators.required],
      profession: ['',Validators.required],
      penalPoints: ['',]
    });
    this.userForm.disable();
  }

  private initPasswordForm(): void{
    this.passwordForm = this.formBuilder.group({
      oldPassword: ['',Validators.required],
      newPassword: ['',Validators.required],
      confPassword: ['',[Validators.required,this.matchPassword('newPass')]]
    });
    this.passwordForm.disable();
  }

  private getUserInfo(): void{
    this.profileService.getUserInfo(this.getUserId()).subscribe(user => {
      this.userForm.patchValue({
        email: user.email,
        username: user.username,
        name: user.name,
        surname: user.surname,
        phone: user.phone,
        city: user.city,
        address: user.address,
        country: user.country,
        profession: user.profession,
        penalPoints: user.penalPoints
      });
    });
  }

  private getUserId(): number{
    return this.authService.user$.value.id;
  }

  changePassword(): void{
    let values = this.passwordForm.value;

    let newPassInfo: NewPasswordInfo = {
      oldPassword: values.oldPassword,
      newPassword: values.newPassword
    };

    this.profileService.changePassword(newPassInfo,this.getUserId()).subscribe({
      next: response => {
        alert(response);
        this.disableEditPassword();
      },
      error: error => {
        alert('Error while changing password!');
        console.log(error);
      }
    });
  }

  changeUserInfo(): void{
    let values = this.userForm.value;

    let userInfo: User = {
      email: values.email,
      username: values.username,
      name: values.name,
      surname: values.surname,
      country: values.country,
      city: values.city,
      address: values.address,
      profession: values.profession,
      phone: values.phone,
      penalPoints: values.penalPoints
    }

    this.profileService.changeInfo(userInfo,this.getUserId()).subscribe({
      next: response => {
        alert(response);
        this.disableEditInfo();
      },
      error: error => {
        alert("Error while changing user info!")
        console.log(error);
      }
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
    this.userForm.enable();
    this.userForm.get("email")?.disable();
    this.userForm.get("username")?.disable();
    this.userForm.get("penalPoints")?.disable();
    this.editInfo = true;
  }

  disableEditInfo() {
    this.userForm.disable();
    this.editInfo = false;
  }

  enableEditPassword() {
    this.passwordForm.enable();
    this.editPassword = true;
  }

  disableEditPassword() {
    this.passwordForm.disable();
    this.editPassword = false;
    this.passwordForm.patchValue({
      newPassword: '',
      confPassword: '',
      oldPassword: '',
    });
  }




}
