import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { DepartementService } from 'src/app/services/departement.service';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-etudiant',
  templateUrl: './etudiant.component.html',
  styleUrls: ['./etudiant.component.scss']
})
export class EtudiantComponent implements OnInit {
  displayedColumns: string[] = ['matricule', 'nom', 'prenom', 'niveau', 'departement', 'editer'];
  //voir dans la page html..
  dataSource: any;
  responseMessage: any;
  selectedFile: File| null=null;
  etudiants: any[]=[];
  selectedEtudiantId: number| null= null;
  imagePreview: string| ArrayBuffer|null =null;


  constructor(private etudiantService: EtudiantService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private departementService: DepartementService,
    private router: Router) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }
  tableData() {

    this.etudiantService.getEtudiants().subscribe((response: any) => {
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
    this.router.navigate(['/gestionEtudiant/ajoutEtudiant']);
  }
  handleEditAction(data:any){
    //
  }

onFileSelected(event: Event): void{
  const input= event.target as HTMLInputElement;
  if(input.files && input.files[0]){
    this.selectedFile= input.files[0];
    const reader= new FileReader();
    reader.onload=(e)=>{
     if(e.target){
      this.imagePreview= e.target?.result;
     }
    };
    reader.readAsDataURL(this.selectedFile);
  }
}

}
