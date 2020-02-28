import { Component, OnInit } from '@angular/core';

import { User } from '../user';
import { RegisterService} from '../register.service';
import {Observable} from 'rxjs';
import {UserController} from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User = new User();
  submitted = false;

  constructor(private registerService: RegisterService) { }

  ngOnInit() { }

/*

  newUser(): void {
    this.user = new User();
  }

  save() {
    this.registerService.registerUser(this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

*/




}
