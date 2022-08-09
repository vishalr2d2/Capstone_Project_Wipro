import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { BidderService } from '../services/bidder.service';
import { FarmerService } from '../services/farmer.service';
import { SnachbarService } from '../services/snachbar.service';
import { GlobalConstants } from '../shared/global-constants';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  password = true;
  confirmPassword = true;
  signupForm: any = FormGroup;
  responseMessage: any;
  hideOrShow: boolean = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private farmerService: FarmerService,
    private bidderService:BidderService,
    private snachbarService: SnachbarService,
    public dialogRef: MatDialogRef<SignupComponent>,
    private ngxService: NgxUiLoaderService
  ) { }

  ngOnInit() {
    this.signupForm = this.formBuilder.group({
      userType: [null, [Validators.required]],
      name: [null, [Validators.required, Validators.pattern(GlobalConstants.nameRegex)]],
      email: [null, [Validators.required, Validators.pattern(GlobalConstants.emailRegex)]],
      contactNumber: [null, [Validators.required, Validators.pattern(GlobalConstants.contactNumberRegex)]],
      address1: [null, [Validators.required]],
      address2: [null, [Validators.required]],
      city: [null, [Validators.required]],
      state: [null, [Validators.required]],
      pinCode: [null, [Validators.required]],
      accountNumber: [null, [Validators.required]],
      ifscCode: [null, [Validators.required]],
      aadhaar: [null, [Validators.required]],
      pan: [null, [Validators.required]],
      certificate: [null, [Validators.required]],
      password: [null, [Validators.required]],
      confirmPassword: [null, [Validators.required]]
    });
  }

  userType(value: any) {
    if (value == "farmer") {
      this.signupForm.addControl('landArea', new FormControl(null, Validators.required));
      this.signupForm.addControl('landAddress', new FormControl(null, Validators.required));
      this.signupForm.addControl('landPinCode', new FormControl(null, Validators.required));
      this.hideOrShow = true;
    }
    else {
      this.signupForm.removeControl('landArea');
      this.signupForm.removeControl('landAddress');
      this.signupForm.removeControl('landPinCode');
      this.hideOrShow = false;
    }
  }

  validateSubmit() {
    if (this.signupForm.controls['password'].value != this.signupForm.controls['confirmPassword'].value) {
      return true;
    }
    else
      return false;
  }

  handleSubmit() {
    this.ngxService.start();
    var formData = this.signupForm.value;
    var data;
    if (this.hideOrShow === true) {
      data = {
        fullName: formData.name,
        contactNo: formData.contactNumber.toString(),
        emailId: formData.email,
        addressOne: formData.address1,
        addressTwo: formData.address2,
        city: formData.city,
        state: formData.state,
        pinCode: formData.pinCode,
        landArea: formData.landArea,
        landAddress: formData.landAddress,
        landPinCode: formData.landPinCode,
        accountNo: formData.accountNumber,
        ifscCode: formData.ifscCode,
        aadhaar: formData.aadhaar,
        pan: formData.pan,
        certificate: formData.certificate,
        password: formData.password
      }
      this.farmerService.register(data).subscribe((response: any) => {
        this.ngxService.stop();
        this.dialogRef.close();
        this.responseMessage = response?.message;
        this.snachbarService.openSnackBar(this.responseMessage, "");
        this.router.navigate(['/']);
      }, (error) => {
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
    else {
      data = {
        fullName: formData.name,
        contactNo: formData.contactNumber.toString(),
        emailId: formData.email,
        addressOne: formData.address1,
        addressTwo: formData.address2,
        city: formData.city,
        state: formData.state,
        pinCode: formData.pinCode,
        accountNo: formData.accountNumber,
        ifscCode: formData.ifscCode,
        aadhaar: formData.aadhaar,
        pan: formData.pan,
        traderLicense: formData.certificate,
        password: formData.password
      }
      this.bidderService.register(data).subscribe((response: any) => {
        this.ngxService.stop();
        this.dialogRef.close();
        this.responseMessage = response?.message;
        this.snachbarService.openSnackBar(this.responseMessage, "");
        this.router.navigate(['/']);
      }, (error) => {
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
}
