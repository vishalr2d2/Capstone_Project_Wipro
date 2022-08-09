import { Component, AfterViewInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { SnachbarService } from '../services/snachbar.service';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements AfterViewInit {

	responseMessage: any;
	data: any;
	ngAfterViewInit() { }

	constructor(
		private ngxService: NgxUiLoaderService,
		private snachbarService: SnachbarService) {
			this.data= localStorage.getItem('fullName');
	}

}
