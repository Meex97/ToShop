import { Component, OnInit } from '@angular/core';
import {ProductInfo} from '../../models/productInfo';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';

import {UserService} from '../../services/user.service';
import {JwtResponse} from '../../response/JwtResponse';

@Component({
  selector: 'app-create-products-customer',
  templateUrl: './create-products-customer.component.html',
  styleUrls: ['./create-products-customer.component.css']
})
export class CreateProductsCustomerComponent implements OnInit {


  product: ProductInfo;
  // private userService: UserService;
  private currentUser: JwtResponse;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService) {
    this.product = new ProductInfo();
  }

  productId: string;


  ngOnInit() {
      this.userService.currentUser.subscribe(client => {
        this.currentUser = client;
      });
      this.product.userId = this.currentUser.id;
      console.log(this.product.userId);
  }

  onSubmit() {
    this.add();
  }


  add() {


    this.product.userId = this.currentUser.id;
    this.productService.createProductCustomer(this.product).subscribe(prod => {


        this.router.navigate(['/']);
      },
      e => {});
  }
}
