import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProvaComponent} from './prova/prova.component';
// import { UserDetailsComponent } from './user-details/user-details.component';
import {HomeComponent} from './home/home.component';
import {AccountComponent} from './account/account.component';
import {ChartComponent} from './chart/chart.component';
import {LoginComponent} from './auth/login/login.component';
import {RegisterComponent} from './auth/register/register.component';
import {ConfirmComponent} from './confirm/confirm.component';
import { AuthGuard } from './auth/auth.guard';
import {ProductsComponent} from './products/products.component';



const routes: Routes = [

  { path: '', redirectTo: 'products', pathMatch: 'full'},
  {path: 'products', canActivate: [AuthGuard], component: ProductsComponent, data: { title: 'List of Products' } },
  // { path: '', redirectTo: 'home', pathMatch: 'full' },  // user
  { path: 'login', component: LoginComponent,  data: { title: 'Login' } },
  { path: 'register', component: RegisterComponent, data: { title: 'Register' } },
  { path: 'user', component: ProvaComponent }, // user
  { path: 'home', component: HomeComponent },
  { path: 'account', component: AccountComponent },
  { path: 'cart', component: ChartComponent },
  { path: 'confirm', component: ConfirmComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
