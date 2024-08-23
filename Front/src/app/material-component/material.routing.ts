
import { Routes } from '@angular/router';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { RouteGuardService } from '../services/route-guard.service';
import { ManageMatiereComponent } from './manage-matiere/manage-matiere.component';


export const MaterialRoutes: Routes = [
    {
        path: '',
        component: ManageMatiereComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['admin']
        }
    }
];
