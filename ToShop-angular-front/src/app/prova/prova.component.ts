import { Component, OnInit } from '@angular/core';

import { User } from '../user';
import { UserController } from '../user.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-prova',
  templateUrl: './prova.component.html',
  styleUrls: ['./prova.component.css']
})
export class ProvaComponent implements OnInit {
  users: Observable<User[]>;
  constructor(private userController: UserController) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userController.getCustomersList();
  }

}
