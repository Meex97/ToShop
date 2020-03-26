import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {ProductService} from '../../services/product.service';
import {JwtResponse} from '../../response/JwtResponse';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {CategoryType} from '../../enum/CategoryType';
import {ProductStatus} from '../../enum/ProductStatus';
import {ProductInfo} from '../../models/productInfo';
import {Role} from '../../enum/Role';

@Component({
    selector: 'app-product.list',
    templateUrl: './product.list.component.html',
    styleUrls: ['./product.list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {

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
            // this.getProds(currentPage, size);
            this.getProdsSupplier(/*currentPage, size*/);
        } else {
            // this.getProds();
            this.getProdsSupplier();
        }
    }

    getProds(page: number = 1, size: number = 5) {
        this.productService.getAllInPage(+page, +size)
            .subscribe(page => {
                this.page = page;
            });

        /*this.page.content.forEach(function(value) {
            console.log(value.idUtente);
        });*/

    }

    getProdsSupplier(/*page: number = 1, size: number = 5*/) {
      this.productService.getAllInPageSupplier(/*+page, +size, */this.productId)
        .subscribe(page => {
          this.page = page;
        });

    }


    remove(productInfo: ProductInfo) {

      this.page = this.page.filter(e => e.productId !== productInfo.productId);

      this.productService.delelte(productInfo).subscribe(_ => {
      },
        err => {
      });
    }


}
