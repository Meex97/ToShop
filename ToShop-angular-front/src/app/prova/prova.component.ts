import { Component, OnInit } from '@angular/core';

import { Cliente } from '../cliente';
import { UserController } from '../user.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-prova',
  templateUrl: './prova.component.html',
  styleUrls: ['./prova.component.css']
})
export class ProvaComponent implements OnInit {
  clients: Observable<Cliente[]>;
  constructor(private userController: UserController) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.clients = this.userController.getClientiList();
  }

}
