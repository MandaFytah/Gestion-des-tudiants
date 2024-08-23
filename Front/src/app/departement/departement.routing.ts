
import { Routes } from '@angular/router';

import { RouteGuardService } from '../services/route-guard.service';
import { DepartementComponent } from './departement.component';



export const DepartementRoutes: Routes = [
    {
        path: '',
        component: DepartementComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['admin']
        }
    }
];
