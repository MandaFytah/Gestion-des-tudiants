import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FullComponent } from './layouts/full/full.component';
import { RouteGuardService } from './services/route-guard.service';

const routes: Routes = [
  { path: '', component: HomeComponent },
  {
    path: 'gestionEtudiant',
    component: FullComponent,
    children: [
      {
        path: '',
        redirectTo: 'student/dashboard',
        pathMatch: 'full',
      },
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule),
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin','user']
        }
      },
    
      { path: "etudiant", 
      loadChildren: () => import('./Modules/student/student.module').then(m => m.StudentModule),
      canActivate:[RouteGuardService],
      data:{
        expectedRole:['admin']
      }

    },
      {
        path: 'gererEtudiant',
        loadChildren: () => import('./GererEtudiant/etudiant/etudiant.module').then(m => m.EtudiantModule),
        canActivate: [RouteGuardService],
        data: {
          expectedRole: ['admin']
        }
      },
    {
      path: 'ajoutEtudiant',
      loadChildren: () => import('./ajout-entite/ajout-etudiant/ajout-etudiant.module').then(m => m.AjoutEtudiantModule),
      canActivate: [RouteGuardService],
      data: {
        expectedRole: ['admin']
      }
    } 
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }