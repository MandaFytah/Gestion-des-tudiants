import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Token, tokenName } from '@angular/compiler';
import { TokenError } from '@angular/compiler/src/ml_parser/lexer';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url= environment.apiUrl

  constructor(private httpClient:HttpClient) { }
  signUp(data:any){
    return this.httpClient.post(this.url+
      "/gestionEtudiant/user/signUp",data,{
        headers:new HttpHeaders().set('Content-Type','application/json')
      })
  }
  forgotPassword(data:any){
    return this.httpClient.post(this.url+
      "/gestionEtudiant/user/forgotPassword",data,{
        headers:new HttpHeaders().set('Content-Type','application/json')
      })
  }
  login(data:any){
    return this.httpClient.post(this.url+
      "/gestionEtudiant/user/login",data,{
        headers:new HttpHeaders().set('Content-Type','application/json')
      })
  }
  checkToken(){
    return this.httpClient.get(this.url+"/gestionEtudiant/user/checktoken"); 
  }
  saveToken(token:string){
    window.localStorage.removeItem(token);
  }
  
  
}
