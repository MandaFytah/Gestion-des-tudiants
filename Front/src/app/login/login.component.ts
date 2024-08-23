import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { AuthService } from '../services/auth.service';
import { DepartementService } from '../services/departement.service';
import { UserService } from '../services/user.service';
import { GlobalConstants } from '../shared/global-constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  hide= true;
  loginForm:any= FormGroup;
  responseMessage:any;

  constructor(private formBuilder :FormBuilder, private router: Router,
    private userService: UserService,
    private authService:AuthService,
    public dialogRef: MatDialogRef<LoginComponent>,
    private ngxService: NgxUiLoaderService,
    private departementService: DepartementService) { }

  ngOnInit(): void {
    console.log('initiaalizing login Form');
    this.loginForm=this.formBuilder.group({
      email:[null,[Validators.required,Validators.pattern(GlobalConstants.emailRegex)]],
      password:[null,[Validators.required]]
    });
    console.log('Login form Initialized',this.loginForm);

  }
  handleSubmit():void{
    console.log('form submited');
    this.ngxService.start();
    console.log('ngxService started');
    var formData= this.loginForm.value;
    console.log('Form data', formData);
    var data={
      email:formData.email,
      password:formData.password
    };
    console.log('data to be sent',data);
    this.userService.login(data).subscribe((response:any)=>{
      console.log('login Successfull');
      this.ngxService.stop();
      console.log('ngxService stopped');
      this.dialogRef.close();
      console.log('dialog closed');
      
      localStorage.setItem('token',response.token);
      console.log('token Stored in a local storage');
      const token=localStorage.getItem('token');
      if(token!=null){
        if(this.authService.getUserRoleFromToken(token)=='admin'){
          this.router.navigate(['/gestionEtudiant/dashboard']);
          console.log('navigated to dashboard');
        }else{
          
          this.router.navigate(['/gestionEtudiant/dashboard']);
          console.log('navigated to student dashboard');
        }
      }
    },(error)=>{
      console.log('login failed', error);
      this.ngxService.stop();
      console.log('stopped 2');
      if(error.error?.message){
        this.responseMessage=error.error.message;
      }else{
        this.responseMessage=GlobalConstants.genericError;
      }
      console.log('Error final',this.responseMessage);
      this.departementService.openDepartement(this.responseMessage,GlobalConstants.error);
    });
  }

}
