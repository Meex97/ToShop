import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProvaComponent} from './prova/prova.component';
import { UserDetailsComponent } from './user-details/user-details.component';


const routes: Routes = [
  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'user', component: ProvaComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
