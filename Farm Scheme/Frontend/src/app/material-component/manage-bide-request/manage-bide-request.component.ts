import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SellService } from 'src/app/services/sell.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { ConfirmationComponent } from '../dialog/confirmation/confirmation.component';
import { PlaceBideRequestComponent } from '../dialog/place-bide-request/place-bide-request.component';
import { PlaceRequestComponent } from '../dialog/place-request/place-request.component';

@Component({
  selector: 'app-manage-bide-request',
  templateUrl: './manage-bide-request.component.html',
  styleUrls: ['./manage-bide-request.component.scss']
})
export class ManageBideRequestComponent implements OnInit {
  displayedColumns: string[] = ['cropType', 'cropName', 'fertilizerType', 'quantity', 'soilPhCertificate','edit'];
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

  handleAddAction(value: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      data: value
    };
    dialogConfig.width = "850px";
    const dialogRef = this.dialog.open(PlaceBideRequestComponent, dialogConfig);
    this.router.events.subscribe(() => {
      dialogRef.close();
    });
    const sub = dialogRef.componentInstance.onAddProduct.subscribe(
      (response) => {
        this.tableData();
      }
    );
  }

}
