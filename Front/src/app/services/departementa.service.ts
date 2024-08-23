import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class DepartementaService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }
  add(data: any) {
    return this.httpClient.post(this.url + "/gestionEtudiant/departement/sauvegarder", data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }
  update(data: any,id:any) {
    return this.httpClient.post(this.url +'/gestionEtudiant/departement/${code}', data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  
  }

  getDepartement(){
    return this.httpClient.get(this.url+'/gestionEtudiant/departement/liste');
  }
  deleteDepartement(id:any){
    return this.httpClient.delete(this.url+"/gestionEtudiant/departement/departement/{id}")
  }
  
}
