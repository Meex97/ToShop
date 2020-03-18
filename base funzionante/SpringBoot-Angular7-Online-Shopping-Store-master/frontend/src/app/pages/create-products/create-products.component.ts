import { Component, OnInit } from '@angular/core';
import {Client} from "../../models/Client";
import {Location} from "@angular/common";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {ProductInfo} from "../../models/productInfo";

@Component({
  selector: 'app-create-products',
  templateUrl: './create-products.component.html',
  styleUrls: ['./create-products.component.css']
})
export class CreateProductsComponent implements OnInit {



  createProduct: ProductInfo;

  constructor( private location: Location,
               private userService: UserService,
               private router: Router) {
    this.createProduct = new ProductInfo();

  }



  ngOnInit() {


  }
  onSubmit() {
    this.userService.signUpClient(this.createProduct).subscribe(u => {
        this.router.navigate(['/login']);
      },
      e => {});
  }

}
