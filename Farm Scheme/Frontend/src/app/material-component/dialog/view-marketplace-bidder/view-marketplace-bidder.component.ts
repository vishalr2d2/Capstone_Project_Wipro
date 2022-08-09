import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { PurchaseService } from 'src/app/services/purchase.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';


@Component({
  selector: 'app-view-marketplace-bidder',
  templateUrl: './view-marketplace-bidder.component.html',
  styleUrls: ['./view-marketplace-bidder.component.scss']
})
export class ViewMarketplaceBidderComponent implements OnInit {
  onAddProduct = new EventEmitter();
  displayedColumns: string[] = ['fullName','amount'];
  dataSource: any;
  responseMessage:any;
  constructor(@Inject(MAT_DIALOG_DATA) public dialogData: any,
  private purchaseService:PurchaseService,
    private ngxService: NgxUiLoaderService,
    public dialogRef: MatDialogRef<ViewMarketplaceBidderComponent>,
    private dialog: MatDialog,
    private snachbarService: SnachbarService,
    private router: Router) { }

  ngOnInit(): void {
    this.tableData();
  }

  tableData() {
    this.purchaseService.getMaxBid(this.dialogData.data.id).subscribe((response: any) => {
      this.ngxService.stop();
      this.dataSource = new MatTableDataSource(response);
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error.error?.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else
        this.responseMessage = GlobalConstants.genericError;
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    })
  }
}
