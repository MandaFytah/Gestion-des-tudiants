import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { DepartementService } from 'src/app/services/departement.service';
import { MatieresService } from 'src/app/services/matieres.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-manage-matiere',
  templateUrl: './manage-matiere.component.html',
  styleUrls: ['./manage-matiere.component.scss']
})
export class ManageMatiereComponent implements OnInit {
  displayedColumns: string[] = ['code', 'nom', 'prof', 'editer'];
  //voir dans la page html..
  dataSource: any;
  responseMessage: any;


  constructor(private matiereService: MatieresService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private departementService: DepartementService,
    private router: Router) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }
  tableData() {

    this.matiereService.getMatieres().subscribe((response: any) => {
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

  }
  handleEditAction(data:any){}
}
