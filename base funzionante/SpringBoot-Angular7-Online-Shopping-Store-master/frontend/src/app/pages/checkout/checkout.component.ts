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




  checked: any = {
    remembered: true
  };
  client: Client;

  productInOrders = [];



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
  }

  checkout() {
    this.cartService.checkout(this.currentUser.account).subscribe(
      _ => {
        this.productInOrders = [];
      },
      error1 => {
        console.log('Checkout Cart Failed');
      });
    this.router.navigate(['/']);
  }


  controlCheck() {
    console.log(this.checked);
  }
}
