import { Component, OnInit } from '@angular/core';

import {Location} from "@angular/common";

import {ActivatedRoute, Router} from "@angular/router";
import {ProductInfo} from "../../models/productInfo";
import {ProductService} from "../../services/product.service";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment";
import {JwtResponse} from "../../response/JwtResponse";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-create-products',
  templateUrl: './create-products.component.html',
  styleUrls: ['./create-products.component.css']
})
export class CreateProductsComponent implements OnInit {

  product: ProductInfo;
  private currentUser: JwtResponse;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) {
      this.product = new ProductInfo();
  }

  productId: string;

  ngOnInit() {

    this.userService.currentUser.subscribe(supplier => {
      this.currentUser = supplier;
    });
    this.product.idUtente = this.currentUser.id;
    console.log(this.product.idUtente);
  }

  onSubmit() {
      this.add();
  }


  add() {
    this.productService.create/*ProductSupplier*/(this.product).subscribe(prod => {

        this.router.navigate(['/seller']);
      },
      e => {});
  }
}
