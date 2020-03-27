import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../services/user.service";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {JwtResponse} from "../../response/JwtResponse";
import {ProductInfo} from "../../models/productInfo";
import {Subscription} from "rxjs";
import {Role} from '../../enum/Role';
import {CategoryType} from '../../enum/CategoryType';
import {ProductStatus} from '../../enum/ProductStatus';
import {ProductClient} from "../../models/ProductClient";

@Component({
  selector: 'app-admin-list',
  templateUrl: './admin-list.component.html',
  styleUrls: ['./admin-list.component.css']
})
export class AdminListComponent implements OnInit, OnDestroy {


  productId: number;

  constructor(private userService: UserService,
              private productService: ProductService,
              private route: ActivatedRoute) {
  }

  Role = Role;
  currentUser: JwtResponse;
  page: Array<ProductClient>;
  CategoryType = CategoryType;
  ProductStatus = ProductStatus;
  private querySub: Subscription;

  ngOnInit() {
    this.userService.currentUser.subscribe(supplier => {
      this.currentUser = supplier;
    });
    this.productId = this.currentUser.id;
    console.log(this.productId);

    this.querySub = this.route.queryParams.subscribe(() => {
      this.getProds();
    });
  }

  ngOnDestroy(): void {
    this.querySub.unsubscribe();
  }

  getProds() {
    this.productService.getAllInPageAdmin()
      .subscribe(page => {
        this.page = page;
      });

  }

  accept(productInfo: ProductClient) {

  }

  decline(productInfo: ProductClient) {

  }
}
