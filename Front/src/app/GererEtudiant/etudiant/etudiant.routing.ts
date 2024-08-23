
import { Routes } from '@angular/router';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { RouteGuardService } from '../../services/route-guard.service';
import { EtudiantComponent } from './etudiant.component';



export const EtudiantRoutes: Routes = [
    {
        path: '',
        component: EtudiantComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['admin']
        }
    }
];
