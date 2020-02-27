import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProvaComponent} from './prova/prova.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import {HomeComponent} from './home/home.component';
import {AccountComponent} from './account/account.component';
import {ChartComponent} from './chart/chart.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {ConfirmComponent} from './confirm/confirm.component';


const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },  // user
  { path: 'user', component: ProvaComponent }, // user
  { path: 'home', component: HomeComponent },
  { path: 'account', component: AccountComponent },
  { path: 'cart', component: ChartComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'confirm', component: ConfirmComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
