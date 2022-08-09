import { Component, OnInit, EventEmitter, Inject, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { InsuranceService } from 'src/app/services/insurance.service';
import { SellService } from 'src/app/services/sell.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-particulars-insuree',
  templateUrl: './particulars-insuree.component.html',
  styleUrls: ['./particulars-insuree.component.scss']
})
export class ParticularsInsureeComponent implements OnInit {
  onAddProduct = new EventEmitter();
  onEditProduct = new EventEmitter();
  productForm: any = FormGroup;
  responseMessage: any;
  categorys: any = [];

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<ParticularsInsureeComponent>,
    private snachbarService: SnachbarService,
    private sellService:SellService,
    private insuranceService:InsuranceService
  ) { }

  ngOnInit() {
    this.productForm = this.formBuilder.group({
      policyNumber: [this.dialogData?.data?.id, [Validators.required]],
      insuranceCompany: [null, [Validators.required]],
      insuredName: [null, [Validators.required]],
      sumInsured: [null, [Validators.required]],
      causeOfLoss:[null,[Validators.required]],
      dateOfLoss:[null,[Validators.required]],
      supportingDocument:[null,[Validators.required]]
    });
    this.details();
  }

  details(){
    this.insuranceService.getInsuranceById(this.dialogData?.data?.id).subscribe((response: any) => {
      this.productForm.controls['insuranceCompany'].setValue(response?.insuranceCompany);
      this.productForm.controls['insuredName'].setValue(response?.insuredName);
      this.productForm.controls['sumInsured'].setValue(response?.sumInsured);
    }, (error: any) => {
      // this.ngxService.stop();
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  handleSubmit() {
    var formData = this.productForm.value;
    var data = {
      id:this.dialogData?.data?.id,
      causeOfLoss: formData.causeOfLoss,
      dateOfLoss: formData.dateOfLoss,
      supportingDocument: formData.supportingDocument,
    }
    this.insuranceService.claimInsurance(data).subscribe((response: any) => {
      this.dialogRef.close();
      this.onAddProduct.emit();
      this.responseMessage = response.message;
      this.snachbarService.openSnackBar(this.responseMessage, "success");
    }, (error) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else
        this.responseMessage = GlobalConstants.genericError;
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    });
  }
}
