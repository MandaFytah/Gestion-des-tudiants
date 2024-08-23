import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { EtudiantService } from 'src/app/services/etudiant.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-ajout-etudiant',
  templateUrl: './ajout-etudiant.component.html',
  styleUrls: ['./ajout-etudiant.component.scss']
})
export class AjoutEtudiantComponent implements OnInit {
  formulaireStudentForm!: FormGroup;
  selectedFile: File | null = null;
  responseMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private etudiantService: EtudiantService,
    private router: Router,
    private ngxService: NgxUiLoaderService
  ) { }

  ngOnInit(): void {
    this.formulaireStudentForm = this.fb.group({
      matricule: [null, Validators.required],
      nom: [null, [Validators.required, Validators.pattern(GlobalConstants.nameregex)]],
      prenom: [''],
      adresse: [''],
      dateNaissance: [''],
      lieu: [''],
      tel: [null, [Validators.required, Validators.pattern(GlobalConstants.telRegex)]],
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      classe: [''],
      photo:[''],
      nomDepartement: [''],
      annee: ['']
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  onSubmit() {
    if (this.formulaireStudentForm.invalid) {
      return;
    }
    const uploadImageData=new FormData();
    if (this.selectedFile) {
      uploadImageData.append("file", this.selectedFile, this.selectedFile.name);
    } else {
      // Gérez le cas où aucun fichier n'est sélectionné, par exemple :
      console.error('No file selected');
    }
    this.ngxService.start();
    const formValue = this.formulaireStudentForm.value;
    if (formValue.dateNaissance) {
      const date = new Date(formValue.dateNaissance);
      formValue.dateNaissance = date.toISOString().split('T')[0];
    }
    var data={
      matricule: formValue.matricule,
      nom: formValue.nom,
      prenom: formValue.prenom,
      adresse: formValue.adresse,
      dateNaissance: formValue.dateNaissance,
      lieu: formValue.lieu,
      tel: formValue.tel,
      email: formValue.email,
      classe: formValue.classe,
      nomDepartement: formValue.nomDepartement,
      annee: formValue.annee,
      photo: null
    }

    this.etudiantService.add(data, this.selectedFile).subscribe((response: any) => {
        this.ngxService.stop();
        this.responseMessage = 'Étudiant sauvegardé avec succès avec l\'image';
        console.log(this.responseMessage);
        this.router.navigate(['/']);
      },
      (error) => {
        this.ngxService.stop();
        if (error.error?.message) {
          this.responseMessage = error.error.message;
        } else {
          this.responseMessage = GlobalConstants.genericError;
        }
        console.error('Erreur lors de la sauvegarde de l\'étudiant', this.responseMessage);
      }
    );
  }
}