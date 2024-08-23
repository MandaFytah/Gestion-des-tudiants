import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './shared/material-module';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { SharedModule } from './shared/shared.module';
import { FullComponent } from './layouts/full/full.component';
import { AppHeaderComponent } from './layouts/full/header/header.component';
import { AppSidebarComponent } from './layouts/full/sidebar/sidebar.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SignUpComponent } from './sign-up/sign-up.component';
import { NgxUiLoaderConfig, NgxUiLoaderModule, SPINNER } from 'ngx-ui-loader';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { LoginComponent } from './login/login.component';
import { TokenInterceptorInterceptor } from './services/token-interceptor.interceptor';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { EtudiantComponent } from './GererEtudiant/etudiant/etudiant.component';
import { DepartementComponent } from './departement/departement.component';
import { AjoutEtudiantComponent } from './ajout-entite/ajout-etudiant/ajout-etudiant.component';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatFormField } from '@angular/material/form-field';
import { ConfirmationComponent } from './material-component/dialog/confirmation/confirmation.component';

const ngxUiLoaderConfig: NgxUiLoaderConfig  ={
  text:"loading...",
  textColor:"#FFFFFF",
  textPosition:"center-center",
  bgsColor:"#7b1ff2",
  fgsColor:"#7b1ff2",
  fgsType:SPINNER.threeStrings,
  fgsSize:100,
  hasProgressBar:false
}

@NgModule({
  declarations: [	
    AppComponent,
    HomeComponent,
    FullComponent,
    ConfirmationComponent,
    AppHeaderComponent,
    AppSidebarComponent,
    SignUpComponent,
    ForgotPasswordComponent,
    LoginComponent,
    EtudiantComponent,
    DepartementComponent,
    AjoutEtudiantComponent,


   ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    FlexLayoutModule,
    SharedModule,
    HttpClientModule,
   
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig)
  ],
  providers: [HttpClientModule,{provide: HTTP_INTERCEPTORS,useClass:TokenInterceptorInterceptor,multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
