import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MaterialModule } from 'src/app/shared/material-module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CdkTableModule } from '@angular/cdk/table';
import { AjoutEtudiantRoute } from './ajout-etudiant.routing';



@NgModule({
  declarations: [],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    RouterModule.forChild(AjoutEtudiantRoute),
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule
  ]
})
export class AjoutEtudiantModule { }
