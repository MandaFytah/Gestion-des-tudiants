import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import jwt_decode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router) { }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return token ? true : false;
  }
  

  public getUserRoleFromToken(token: string): string {
    const decodedToken: any = jwt_decode(token);
    console.log('Decoded Token: ',decodedToken.role)
    return decodedToken.role; // Assuming the role is stored in the token under "role"
  }
  
  
}