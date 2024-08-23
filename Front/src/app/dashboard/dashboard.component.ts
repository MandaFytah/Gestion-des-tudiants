import { Component, AfterViewInit } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { NgxUiLoaderBlurredDirective } from 'ngx-ui-loader/lib/core/ngx-ui-loader-blurred.directive';
import { DashboardService } from '../services/dashboard.service';
import { DepartementService } from '../services/departement.service';
import { GlobalConstants } from '../shared/global-constants';
@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements AfterViewInit {
	responseMessage:any;
	data:any;

	ngAfterViewInit() { }

	constructor(private dashboardService:DashboardService,
		private ngxService:NgxUiLoaderService,
		private departementService:DepartementService) {
			this.ngxService.start();
			this.dashboardData();
	}
	dashboardData(){
		this.ngxService.stop();
		/*this.dashboardService.getDetails().subscribe((response:any)=>{
			this.ngxService.stop();
			this.data=response;
		},(error:any)=>{
			this.ngxService.stop();
			console.log(error);
			if(error.error?.message){
				this.responseMessage=error.error?.message;
			}else{
				this.responseMessage=GlobalConstants.genericError;
			}
			this.departementService.openDepartement(this.responseMessage,GlobalConstants.error);
		})*/
	}
}
