import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EtudiantService {
  url = environment.apiUrl;

  constructor(private httpClient: HttpClient) { }

  add(etudiant: any, file: File | null): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('etudiant', new Blob([JSON.stringify(etudiant)], { type: 'application/json' }));
    if (file) {
        formData.append('file', file);
    }
    return this.httpClient.post(`${this.url}/gestionEtudiant/etudiants/sauvegarder`, formData);
}

  update(data: any, id: any) {
    return this.httpClient.post(`${this.url}/gestionEtudiant/etudiants/update/${id}`, data, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  getEtudiants() {
    return this.httpClient.get(`${this.url}/gestionEtudiant/etudiants`);
  }

  deleteEtudiants(id: any) {
    return this.httpClient.delete(`${this.url}/gestionEtudiant/etudiants/${id}`);
  }

  /*uploadImage(id: number, file: File) {
    const formData: FormData = new FormData();
    formData.append('file', file);
    return this.httpClient.post(`${this.url}/gestionEtudiant/etudiants/uploadImage/${id}`, formData);
  }*/
}