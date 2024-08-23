import { Routes } from "@angular/router";
import { AjoutEtudiantComponent } from "./ajout-etudiant.component";
import { RouteGuardService } from "src/app/services/route-guard.service";

export const AjoutEtudiantRoute: Routes = [
    {
        path: '',
        component: AjoutEtudiantComponent,
        canActivate: [RouteGuardService],
        data: {
            expectedRole: ['admin']
        }
    }
];