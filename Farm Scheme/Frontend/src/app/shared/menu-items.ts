import { Injectable } from '@angular/core';

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  role: string;
}

const MENUITEMS = [
  //Farmer
  { state: 'dashboard', name: 'Dashboard', type: 'link', icon: 'dashboard', role: '' },
  { state: 'placeSellRequest', type: 'link', name: 'Place Sell Request', icon: 'category', role: 'farmer' },
  { state: 'viewSoldCropHistoryComponent', type: 'link', name: 'View Sold History', icon: 'inventory_2', role: 'farmer' },
  { state: 'viewMarketplaceComponent', type: 'link', name: 'View Marketplace', icon: 'shopping_cart', role: 'farmer' },
  { state: 'insurance', type: 'link', name: 'Manage Insurance', icon: 'receipt_long', role: 'farmer' },
  //User
  { state: 'placeBideRequest', type: 'link', name: 'Place Bide Request', icon: 'shopping_cart', role: 'bidder' },
  { state: 'viewPurchaseCropHistoryComponent', type: 'link', name: 'View Purchase History', icon: 'inventory_2', role: 'bidder' },
  { state: 'viewMarketplaceComponent', type: 'link', name: 'View Marketplace', icon: 'shopping_cart', role: 'bidder' },
];

@Injectable()
export class MenuItems {
  getMenuitem(): Menu[] {
    return MENUITEMS;
  }
}
