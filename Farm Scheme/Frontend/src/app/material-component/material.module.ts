import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CdkTableModule } from '@angular/cdk/table';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialRoutes } from './material.routing';
import { MaterialModule } from '../shared/material-module';
import { ConfirmationComponent } from './dialog/confirmation/confirmation.component';
import { PlaceRequestComponent } from './dialog/place-request/place-request.component';
import { ManagePlaceRequestComponent } from './manage-place-request/manage-place-request.component';
import { ManageBideRequestComponent } from './manage-bide-request/manage-bide-request.component';
import { PlaceBideRequestComponent } from './dialog/place-bide-request/place-bide-request.component';
import { ViewBidRequestComponent } from './dialog/view-bid-request/view-bid-request.component';
import { ViewSoldCropHistoryComponent } from './view-sold-crop-history/view-sold-crop-history.component';
import { ViewPurchaseCropHistoryComponent } from './view-purchase-crop-history/view-purchase-crop-history.component';
import { ViewMarketplaceComponent } from './view-marketplace/view-marketplace.component';
import { ViewMarketplaceBidderComponent } from './dialog/view-marketplace-bidder/view-marketplace-bidder.component';
import { ManageInsuranceComponent } from './manage-insurance/manage-insurance.component';
import { InsuranceComponent } from './dialog/insurance/insurance.component';
import { ParticularsInsureeComponent } from './dialog/particulars-insuree/particulars-insuree.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(MaterialRoutes),
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule
  ],
  providers: [],
  declarations: [
    ConfirmationComponent,
    PlaceRequestComponent,
    ManagePlaceRequestComponent,
    ManageBideRequestComponent,
    PlaceBideRequestComponent,
    ViewBidRequestComponent,
    ViewSoldCropHistoryComponent,
    ViewPurchaseCropHistoryComponent,
    ViewMarketplaceComponent,
    ViewMarketplaceBidderComponent,
    ManageInsuranceComponent,
    InsuranceComponent,
    ParticularsInsureeComponent,
    
  ]
})
export class MaterialComponentsModule {}
