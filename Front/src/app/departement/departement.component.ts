import { Component, OnInit } from '@angular/core';
import { DepartementService } from '../services/departement.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { MatDialog } from '@angular/material/dialog';
import { DepartementaService } from '../services/departementa.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { GlobalConstants } from '../shared/global-constants';

@Component({
  selector: 'app-departement',
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.scss']
})
export class DepartementComponent implements OnInit {

  displayedColumns: string[] = ['code', 'nom', 'etablissement', 'editer'];
  //voir dans la page html..
  dataSource: any;
  responseMessage: any;
 
  selectedEtudiantId: number| null= null;
  


  constructor(private departementService: DepartementService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private departementaService: DepartementaService,
    private router: Router) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }
  tableData() {

    this.departementaService.getDepartement().subscribe((response: any) => {
      this.ngxService.stop(); this.dataSource = new MatTableDataSource(response);
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      } else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.departementService.openDepartement(this.responseMessage, GlobalConstants.error);
    });
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  handleAddAction() {
//
  }
  handleEditAction(data:any){
    //
  }

}
