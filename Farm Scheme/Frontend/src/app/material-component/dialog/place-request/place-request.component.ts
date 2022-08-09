import { Component, OnInit, EventEmitter, Inject, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SellService } from 'src/app/services/sell.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-place-request',
  templateUrl: './place-request.component.html',
  styleUrls: ['./place-request.component.scss']
})
export class PlaceRequestComponent implements OnInit {
  onAddProduct = new EventEmitter();
  onEditProduct = new EventEmitter();
  productForm: any = FormGroup;
  responseMessage: any;
  categorys: any = [];

  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<PlaceRequestComponent>,
    private snachbarService: SnachbarService,
    private sellService:SellService
  ) { }

  ngOnInit() {
    this.productForm = this.formBuilder.group({
      cropType: [null, [Validators.required]],
      cropName: [null, [Validators.required]],
      fertilizerType: [null, [Validators.required]],
      quantity: [null, Validators.required],
      soilPhCertificate:[null,Validators.required]
    });
  }

  handleSubmit() {
    var formData = this.productForm.value;
    var data = {
      farmerId:localStorage.getItem('id'),
      cropType: formData.cropType,
      cropName: formData.cropName,
      fertilizerType: formData.fertilizerType,
      quantity: formData.quantity,
      soilPhCertificate: formData.soilPhCertificate
    }
    this.sellService.sellRequest(data).subscribe((response: any) => {
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
