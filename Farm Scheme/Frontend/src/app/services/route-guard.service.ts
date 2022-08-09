import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { GlobalConstants } from '../shared/global-constants';
import { SnachbarService } from './snachbar.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate {
  constructor(public router: Router, private snachbarService: SnachbarService) { }
  canActivate(route: ActivatedRouteSnapshot): boolean {

    if (localStorage.getItem('id') != undefined) {
      return true;
    }
    else {
      this.router.navigate(['/']);
      localStorage.clear();
      return false;
    }
  }

}
