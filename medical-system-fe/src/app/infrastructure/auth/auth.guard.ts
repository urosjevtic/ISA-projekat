import { Injectable } from '@angular/core';
import {
  CanActivate,
  UrlTree,
  Router,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { User } from './model/user.model';

@Injectable({
    providedIn: 'root',
  })
export class AuthGuard implements CanActivate{
    constructor(
        private router: Router,
        private authService: AuthService
      ) {}
      canActivate():
      | Observable<boolean | UrlTree>
      | Promise<boolean | UrlTree>
      | boolean
      | UrlTree {
          
      const user: User = this.authService.user$.getValue();
      if (user.username === '') {
        this.router.navigate(['login']);
        return false;
      }
      return true;
    }
}
