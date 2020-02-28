import { Component, OnInit } from '@angular/core';
import { Product } from './product';
import { ProductService } from '../product.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
// To display a list of products to the Angular 8 template.

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  data: Product[] = [];
  displayedColumns: string[] = ['prodName', 'prodDesc', 'prodPrice'];
  isLoadingResults = true;

  //  inject the Product and Auth Services to the constructor.
  constructor(private productService: ProductService, private authService: AuthService, private router: Router) { }



  // Create a function for consuming or get a product list from the producing service.
  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => {
        this.data = products;
        console.log(this.data);
        this.isLoadingResults = false;
      }, err => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }

  // call the function on ngOnInit()
  ngOnInit() {
    this.getProducts();
  }


  // add a function for log out the current session
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
}
