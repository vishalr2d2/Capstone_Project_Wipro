import { Routes } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { RouteGuardService } from '../services/route-guard.service';
import { ManageBideRequestComponent } from './manage-bide-request/manage-bide-request.component';
import { ManageInsuranceComponent } from './manage-insurance/manage-insurance.component';

import { ManagePlaceRequestComponent } from './manage-place-request/manage-place-request.component';
import { ViewMarketplaceComponent } from './view-marketplace/view-marketplace.component';
import { ViewPurchaseCropHistoryComponent } from './view-purchase-crop-history/view-purchase-crop-history.component';
import { ViewSoldCropHistoryComponent } from './view-sold-crop-history/view-sold-crop-history.component';


export const MaterialRoutes: Routes = [
  {
    path: 'placeSellRequest',
    component: ManagePlaceRequestComponent,
    canActivate: [RouteGuardService],
  },
  {
    path: 'placeBideRequest',
    component: ManageBideRequestComponent,
    canActivate: [RouteGuardService],
  },
  {
    path: 'viewSoldCropHistoryComponent',
    component: ViewSoldCropHistoryComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'viewPurchaseCropHistoryComponent',
    component: ViewPurchaseCropHistoryComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'viewMarketplaceComponent',
    component: ViewMarketplaceComponent,
    canActivate: [RouteGuardService]
  },
  {
    path: 'insurance',
    component: ManageInsuranceComponent,
    canActivate: [RouteGuardService]
  },
  { path: '**', component: DashboardComponent }
];
