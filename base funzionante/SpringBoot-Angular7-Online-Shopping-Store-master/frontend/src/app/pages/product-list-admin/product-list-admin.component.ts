import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute} from '@angular/router';
import {JwtResponse} from '../../response/JwtResponse';
import {ProductInfo} from '../../models/productInfo';
import {Subscription} from 'rxjs';
import {CategoryType} from '../../enum/CategoryType';
import {ProductStatus} from '../../enum/ProductStatus';

import {Role} from '../../enum/Role';

@Component({
  selector: 'app-product-list-admin',
  templateUrl: './product-list-admin.component.html',
  styleUrls: ['./product-list-admin.component.css']
})
export class ProductListAdminComponent implements OnInit, OnDestroy {


  productId: number;

  constructor(private userService: UserService,
              private productService: ProductService,
              private route: ActivatedRoute) {
  }

  Role = Role;
  currentUser: JwtResponse;
  page: Array<ProductInfo>;
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
      this.update();
    });
  }

  ngOnDestroy(): void {
    this.querySub.unsubscribe();
  }

  update() {
    if (this.route.snapshot.queryParamMap.get('page')) {
      const currentPage = +this.route.snapshot.queryParamMap.get('page');
      const size = +this.route.snapshot.queryParamMap.get('size');
      this.getProds(currentPage, size);
    } else {
      this.getProds();

    }
  }

  getProds(page: number = 1, size: number = 5) {
    this.productService.getAllInPage(+page, +size)
      .subscribe(page => {
        this.page = page;
      });
  }

}
