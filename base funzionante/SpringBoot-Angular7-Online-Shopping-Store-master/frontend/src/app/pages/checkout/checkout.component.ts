import {Component, NgModule, OnInit} from '@angular/core';
import {JwtResponse} from '../../response/JwtResponse';
import {ProductInfo} from '../../models/productInfo';
import {Subscription} from 'rxjs';
import {UserService} from '../../services/user.service';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Role} from '../../enum/Role';
import {CartService} from '../../services/cart.service';
import {Client} from '../../models/Client';
import {FormsModule, NgForm} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {PaymentMethodsType} from '../../enum/PaymentMethodsType';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule
  ]
})

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})

export class CheckoutComponent implements OnInit {

  Role = Role;
  currentUser: JwtResponse;
  page: Array<ProductInfo>;

  client = new Client();

  productInOrders = [];

  marked = false;
  theCheckbox = false;

  total: number;
  discount: number;

  paymentMethodType: PaymentMethodsType;


  constructor(private userService: UserService,
              private productService: ProductService,
              private route: ActivatedRoute,
              private cartService: CartService,
              private router: Router) {

    // this.checked = true;
  }

  ngOnInit() {
    this.userService.currentUser.subscribe(client => {
      this.currentUser = client;
    });

    this.userService.getClient(this.currentUser.account).subscribe(u => {
      this.client = u;
    });

    this.total = 0;
    this.cartService.getCart().subscribe(prods => {
      console.log(prods);
      this.productInOrders = prods;

    });
   // this.total = this.productInOrders.reduce((prev, cur) => prev + cur.count * cur.productPrice, 0);
  }

  checkout() {
    console.log(this.marked);
    console.log(this.theCheckbox);
    /*this.cartService.checkout(this.currentUser.account).subscribe(
      _ => {
        this.productInOrders = [];
      },
      error1 => {
        console.log('Checkout Cart Failed');
      });
    this.router.navigate(['/']);*/
  }

  toggleVisibility(e) {
    this.theCheckbox = e.target.checked;
    this.marked = e.target.checked;
    this.discount = this.client.credits / 10 ;
    this.total = this.productInOrders.reduce((prev, cur) => prev + cur.count * cur.productPrice, 0);
    if (this.total < this.discount ) {
      this.discount = this.total;
    }
    console.log(this.discount);
  }

}
