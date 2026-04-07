import { Routes } from '@angular/router';
import {Login} from './component/layout/login/login';
import {Bet} from './component/layout/bet/bet';

export const routes: Routes = [

   {path: "",redirectTo:"login",pathMatch: "full"},
   {path: "login",component: Login},
   {path: "Bet",component: Bet},
   
];