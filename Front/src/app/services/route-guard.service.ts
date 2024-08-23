import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const expectedRoleArray = route.data.expectedRole;
    const token = localStorage.getItem('token');
    console.log('expected roles: ',expectedRoleArray)
    
    // Validate token presence
    if (!token) {
      this.router.navigate(['/']);
      return false;
    }
    
    // Validate user role
    const userRole = this.authService.getUserRoleFromToken(token);
    if (expectedRoleArray.includes(userRole)) {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }
}