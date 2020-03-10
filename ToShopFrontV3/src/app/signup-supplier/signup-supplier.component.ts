import { Component, OnInit } from '@angular/core';
import {AuthService} from '../_services/auth.service';

@Component({
  selector: 'app-signup-supplier',
  templateUrl: './signup-supplier.component.html',
  styleUrls: ['./signup-supplier.component.css']
})
export class SignupSupplierComponent implements OnInit {

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }


  ngOnInit() {
  }

  onSubmit() {
    this.authService.registerSupplier(this.form).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
