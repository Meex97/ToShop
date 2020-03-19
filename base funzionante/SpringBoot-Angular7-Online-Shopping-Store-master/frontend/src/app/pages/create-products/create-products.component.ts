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
  isEdit = false;




 /* constructor( private location: Location,
               private productService: ProductService,
               private router: Router) {
    this.product = new ProductInfo();

  }*/



  ngOnInit() {


  }

  onSubmit() {
      this.add();
  }


/*  add() {
    this.productService.createProductSupplier(this.product).subscribe(prod => {
        this.router.navigate(['/']);
        // this.router.navigate(['/login']);
      },
      e => {});
  }
  */
  add() {
    this.productService.create(this.product).subscribe(prod => {
        // if (!prod) throw new Error;
        this.router.navigate(['/']);
      },
      e => {
      console.log('sono esploso');
      });
  }

  /*onSubmit() {
    this.productService.createProductSupplier(this.product).subscribe(u => {
        this.router.navigate(['/login']);
      },
      e => {});
  }*/

}
