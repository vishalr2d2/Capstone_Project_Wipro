import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { GlobalConstants } from '../shared/global-constants';
import { SnachbarService } from '../services/snachbar.service';
import { FarmerService } from '../services/farmer.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  hide = true;
  loginForm: any = FormGroup;
  responseMessage: any;
  hideOrShow:any;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private farmerService: FarmerService,
    public dialogRef: MatDialogRef<LoginComponent>,
    private ngxService: NgxUiLoaderService,
    private snachbarService: SnachbarService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      userType: [null, Validators.required],
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      password: [null, Validators.required]
    });
  }

  userType(value: any) {
    if (value == "farmer") {
      this.hideOrShow = true;
    }
    else {
      this.hideOrShow = false;
    }
  }

  handleSubmit() {
    this.ngxService.start();
    var formData = this.loginForm.value;
    var data = {
      role: formData.userType,
      emailId: formData.email,
      password: formData.password
    }
    this.farmerService.login(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.dialogRef.close();
      localStorage.setItem('emailId', response.emailId);
      localStorage.setItem('role', response.role);
      localStorage.setItem('fullName', response.fullName);
      localStorage.setItem('id', response.id);
      this.router.navigate(['/farmScheme/dashboard']);
    }, (error) => {
      console.log(error);
      this.ngxService.stop();
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    });
  }
}
