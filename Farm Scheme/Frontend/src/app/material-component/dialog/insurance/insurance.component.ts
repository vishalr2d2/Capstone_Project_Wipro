import { Component, OnInit, EventEmitter, Inject, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CropService } from 'src/app/services/crop.service';
import { InsuranceService } from 'src/app/services/insurance.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-insurance',
  templateUrl: './insurance.component.html',
  styleUrls: ['./insurance.component.scss']
})
export class InsuranceComponent implements OnInit {
  onAddProduct = new EventEmitter();
  onEditProduct = new EventEmitter();
  categoryForm: any = FormGroup;
  dialogAction: any = "Add";
  action: any = "Add";
  crops:any;

  calculatedData:any;

  responseMessage: any;
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
    private formBuilder: FormBuilder,
    private cropService: CropService,
    public dialogRef: MatDialogRef<InsuranceComponent>,
    private snachbarService: SnachbarService,
    private insuranceService:InsuranceService
  ) { }

  ngOnInit() {
    this.categoryForm = this.formBuilder.group({
      season:[null,[Validators.required]],
      year:[null,[Validators.required]],
      cropName:[null,[Validators.required]],
      // sumInsured: [null, [Validators.required]],
      area:[null,[Validators.required]]
    });
    this.tableData();
  }

  tableData() {
    this.cropService.getAllCrop().subscribe((response: any) => {
      this.crops = response;
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else
        this.responseMessage = GlobalConstants.genericError;
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }

  handleCalculateSubmit() {
    var formData = this.categoryForm.value;
    var data = {
      season: formData.season,
      year:formData.year,
      cropName:formData.cropName,
      area:formData.area
    }
    this.insuranceService.calculate(data).subscribe((response: any) => {
      this.calculatedData=response;
      // this.dialogRef.close();
      // this.onAddProduct.emit();
      // this.responseMessage = response.message;
      // this.snachbarService.openSnackBar(this.responseMessage, "success");
    }, (error) => {
      this.dialogRef.close();
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else
        this.responseMessage = GlobalConstants.genericError;
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    });
  }

  handleApplyAction(){
    var formData = this.categoryForm.value;
    var data = {
      premiumAmount: this.calculatedData?.premiumAmount,
      area: formData.area,
      sumInsured: this.calculatedData?.sumInsured,
      sumInsuredPerHectare: this.calculatedData?.sumInsuredPerHectare,
      cropName: formData.cropName,
      insuranceCompany: this.calculatedData?.insuranceCompany,
      sharePremium: this.calculatedData?.sharePremium,
      fullName: localStorage.getItem('fullName'),
      farmerId: localStorage.getItem('id')
    }
    this.insuranceService.applyForInsurance(data).subscribe((response: any) => {
      this.dialogRef.close();
      this.onAddProduct.emit();
      this.responseMessage = response.message;
      this.snachbarService.openSnackBar(this.responseMessage, "success");
    }, (error) => {
      this.dialogRef.close();
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else
        this.responseMessage = GlobalConstants.genericError;
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    });
  }

  // add() {
  //   var formData = this.categoryForm.value;
  //   var data = {
  //     name: formData.name
  //   }
  //   this.categoryService.add(data).subscribe((response: any) => {
  //     this.dialogRef.close();
  //     this.onAddProduct.emit();
  //     this.responseMessage = response.message;
  //     this.snachbarService.openSnackBar(this.responseMessage, "success");
  //   }, (error) => {
  //     this.dialogRef.close();
  //     console.log(error);
  //     if (error.error?.message) {
  //       this.responseMessage = error.error?.message;
  //     }
  //     else
  //       this.responseMessage = GlobalConstants.genericError;
  //     this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
  //   });
  // }

  // edit() {
  //   var formData = this.categoryForm.value;
  //   var data = {
  //     id: this.dialogData.data.id,
  //     name: formData.name,
  //     price: formData.price,
  //     description: formData.description
  //   }
  //   this.categoryService.update(data).subscribe((response: any) => {
  //     this.dialogRef.close();
  //     this.onEditProduct.emit();
  //     this.responseMessage = response.message;
  //     this.snachbarService.openSnackBar(this.responseMessage, "success");
  //   }, (error) => {
  //     this.dialogRef.close();
  //     console.log(error);
  //     if (error.error?.message) {
  //       this.responseMessage = error.error?.message;
  //     }
  //     else
  //       this.responseMessage = GlobalConstants.genericError;
  //     this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
  //   });
  // }
}
