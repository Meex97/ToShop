import { Component, OnInit, Input } from '@angular/core';
import { UserController} from '../user.service';
import {User} from '../user';

import { ProvaComponent} from '../prova/prova.component';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})

export class UserDetailsComponent implements OnInit {

  @Input() user: User;

  constructor(private userController: UserController, private listComponent: ProvaComponent) { }

  ngOnInit() {
  }

  /*updateActive(isActive: boolean) {
    this.customerService.updateCustomer(this.customer.id,
      { name: this.customer.name, age: this.customer.age, active: isActive })
      .subscribe(
        data => {
          console.log(data);
          this.customer = data as Customer;
        },
        error => console.log(error));
  }*/

  /*deleteCustomer() {
    this.customerService.deleteCustomer(this.customer.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }*/
}
