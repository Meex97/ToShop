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

  // AGGIUNTI DA ME
  fileToUpload: File = null;
  showAdd = false;
  auth: string;
  imageUrl =  '/assets/img/noimage.png';
  // FINE

  ngOnInit() {
      this.userService.currentUser.subscribe(client => {
        this.currentUser = client;
      });
      this.product.idUtente = this.currentUser.id;
      this.product.nameUtente = this.currentUser.name;
  }

  handleFileInput(file: FileList) {
    this.product.productimage = file.item(0);
    // ho sostituito var con const
    const reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    };
    reader.readAsDataURL(this.product.productimage);
  }

  onSubmit() {
    this.product.productStatus = 0;
    this.add();
  }


  add() {


    this.product.idUtente = this.currentUser.id;
    this.productService.createProductCustomer(this.product).subscribe(prod => {


        this.router.navigate(['/customer/product']);
      },
      e => {});
  }
}
