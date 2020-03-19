import { Component, OnInit } from '@angular/core';

import {Location} from "@angular/common";

import {ActivatedRoute, Router} from "@angular/router";
import {ProductInfo} from "../../models/productInfo";
import {ProductService} from "../../services/product.service";
import {Observable} from "rxjs";
import {apiUrl} from "../../../environments/environment";

@Component({
  selector: 'app-create-products',
  templateUrl: './create-products.component.html',
  styleUrls: ['./create-products.component.css']
})
export class CreateProductsComponent implements OnInit {

  product: ProductInfo;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) {
      this.product = new ProductInfo();
  }

  productId: string;

  ngOnInit() {
  }

  onSubmit() {
      this.add();
  }


  add() {
    this.productService.create/*ProductSupplier*/(this.product).subscribe(prod => {

        this.router.navigate(['/']);
      },
      e => {});
  }
}
