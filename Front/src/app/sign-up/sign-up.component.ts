import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { DepartementService } from '../services/departement.service';
import { UserService } from '../services/user.service';
import { GlobalConstants } from '../shared/global-constants';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  password= true;
  confirmPassword = true;
  signUpForm: any= FormGroup;
  responseMessage: any;

  constructor(private formBuilder:FormBuilder,
    private router: Router,
    private userService: UserService, 
    private departementService: DepartementService,
    public dialogRef: MatDialogRef<SignUpComponent>,
    private ngxService : NgxUiLoaderService
    ) { }

  ngOnInit(): void {
    this.signUpForm= this.formBuilder.group({
      name:[null,[Validators.required,Validators.pattern(GlobalConstants.nameregex)]],
      email:[null,[Validators.required,Validators.pattern(GlobalConstants.emailRegex)]],
      tel:[null,[Validators.required,Validators.pattern(GlobalConstants.telRegex)]],
      password:[null,[Validators.required]],
      confirmPassword: [null,[Validators.required]]
    })
  
  }
  validateSubmit(){
    if(this.signUpForm.controls['password'].value !=this.signUpForm.controls['confirmPassword'].value){
      return true;
    }else{
      return false;
    }
  }
  handleSubmit(){
    this.ngxService.start();
    var formData= this.signUpForm.value;
    var data ={
      name: formData.name,
      email:formData.email,
      tel : formData.tel,
      password : formData.password
    }
    this.userService.signUp(data).subscribe((response: any)=>{
      this.ngxService.stop();
      this.dialogRef.close();
      this.responseMessage =response?.message;this.departementService.openDepartement(this.responseMessage,"");
      this.router.navigate(['/']);
    },(error)=>{
      this.ngxService.stop();
      if(error.error?.message){
        this.responseMessage= error.error?.message;
      }else{
        this.responseMessage=GlobalConstants.genericError;
      }
      this.departementService.openDepartement(this.responseMessage,GlobalConstants.error);
    })
  }

}
