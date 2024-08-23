import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

  constructor(private departement: MatSnackBar) { }
  openDepartement(message: string, action: string) {
    if (action === 'error') {
      this.departement.open(message, '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 2000,
        panelClass: ['black-departement']
      });
    } else {
      this.departement.open(message, '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 2000,
        panelClass: ['green-departement']
      });
    }
  }
}
