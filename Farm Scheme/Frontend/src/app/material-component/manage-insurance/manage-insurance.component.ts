import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { InsuranceService } from 'src/app/services/insurance.service';
import { SellService } from 'src/app/services/sell.service';
import { SnachbarService } from 'src/app/services/snachbar.service';
import { GlobalConstants } from 'src/app/shared/global-constants';
import { InsuranceComponent } from '../dialog/insurance/insurance.component';
import { ParticularsInsureeComponent } from '../dialog/particulars-insuree/particulars-insuree.component';

@Component({
  selector: 'app-manage-insurance',
  templateUrl: './manage-insurance.component.html',
  styleUrls: ['./manage-insurance.component.scss']
})
export class ManageInsuranceComponent implements OnInit {
  displayedColumns: string[] = ['id','insuranceCompany', 'cropName', 'area', 'sumInsuredPerHectare', 'sharePremium','premiumAmount','sumInsured','edit'];
  dataSource: any;
  length1: any;
  responseMessage: any;

  constructor(
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private snachbarService: SnachbarService,
    private router: Router,
    private insuranceService:InsuranceService) { }

  ngOnInit(): void {
    this.ngxService.start();
    this.tableData();
  }

  tableData() {
    this.insuranceService.getInsuranceById(localStorage.getItem('id')).subscribe((response: any) => {
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
    const dialogRef = this.dialog.open(InsuranceComponent, dialogConfig);
    this.router.events.subscribe(() => {
      dialogRef.close();
    });
    const sub = dialogRef.componentInstance.onAddProduct.subscribe(
      (response) => {
        this.tableData();
      }
    );
  }

  handleApproveAction(value: any) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = {
      data: value
    };
    dialogConfig.width = "850px";
    const dialogRef = this.dialog.open(ParticularsInsureeComponent, dialogConfig);
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
