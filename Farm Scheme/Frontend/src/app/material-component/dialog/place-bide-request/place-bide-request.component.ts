import { Component, OnInit, EventEmitter, Inject, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PurchaseService } from 'src/app/services/purchase.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';

@Component({
  selector: 'app-place-bide-request',
  templateUrl: './place-bide-request.component.html',
  styleUrls: ['./place-bide-request.component.scss']
})
export class PlaceBideRequestComponent implements OnInit {
  onAddProduct = new EventEmitter();
  categoryForm: any = FormGroup;

  responseMessage: any;
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
    private formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<PlaceBideRequestComponent>,
    private snachbarService: SnachbarService,
    private purchaseService: PurchaseService
  ) { }

  ngOnInit() {
    this.categoryForm = this.formBuilder.group({
      amount: [null, [Validators.required]]
    });
    console.log(this.dialogData?.data);
  }

  handleSubmit() {
    var formData = this.categoryForm.value;
    var data = {
      farmerId: this.dialogData?.data.farmerId,
      cropType: this.dialogData?.data.cropType,
      cropName: this.dialogData?.data.cropName,
      fertilizerType: this.dialogData?.data.fertilizerType,
      quantity: this.dialogData?.data.quantity,
      soilPhCertificate: this.dialogData?.data.soilPhCertificate,
      bidderId: localStorage.getItem('id'),
      amount: formData.amount,
      sellRequestId: this.dialogData?.data.id,
      fullName:localStorage.getItem('fullName')
    }
    this.purchaseService.purchaseRequest(data).subscribe((response: any) => {
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

}
