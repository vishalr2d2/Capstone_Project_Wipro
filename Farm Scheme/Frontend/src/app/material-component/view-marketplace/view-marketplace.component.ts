import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SellService } from 'src/app/services/sell.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { PlaceRequestComponent } from '../dialog/place-request/place-request.component';
import { ViewBidRequestComponent } from '../dialog/view-bid-request/view-bid-request.component';
import { ViewMarketplaceBidderComponent } from '../dialog/view-marketplace-bidder/view-marketplace-bidder.component';

@Component({
  selector: 'app-view-marketplace',
  templateUrl: './view-marketplace.component.html',
  styleUrls: ['./view-marketplace.component.scss']
})
export class ViewMarketplaceComponent implements OnInit {
  displayedColumns: string[] = ['cropType', 'cropName', 'fertilizerType', 'quantity', 'soilPhCertificate','status','edit'];
  dataSource: any;
  length1: any;
  responseMessage: any;

  constructor(
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private snachbarService: SnachbarService,
    private router: Router,
    private sellService:SellService) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }

  tableData() {
    this.sellService.getAllSellRequest().subscribe((response: any) => {
      this.ngxService.stop();
      this.dataSource = new MatTableDataSource(response);
    }, (error: any) => {
      this.ngxService.stop();
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

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  handleAddAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      action: 'Add'
    };
    dialogConfig.width = "850px";
    const dialogRef = this.dialog.open(PlaceRequestComponent, dialogConfig);
    this.router.events.subscribe(() => {
      dialogRef.close();
    });
    const sub = dialogRef.componentInstance.onAddProduct.subscribe(
      (response) => {
        this.tableData();
      }
    );
  }

  handleViewAction(value: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      data: value
    };
    dialogConfig.width = "850px";
    const dialogRef = this.dialog.open(ViewMarketplaceBidderComponent, dialogConfig);
    this.router.events.subscribe(() => {
      dialogRef.close();
    });
  }

}
