import { Component, OnInit } from '@angular/core';

import { User } from '../user';
import { UserService } from '../user.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-prova',
  templateUrl: './prova.component.html',
  styleUrls: ['./prova.component.css']
})
export class ProvaComponent implements OnInit {

  constructor(private userService: UserService) { }
  users: Observable<User[]>;
  string: Observable<any>;

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getCustomersList();
    this.string = this.userService.getCustomersList();
  }

}
