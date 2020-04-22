import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavigationComponent} from './parts/navigation/navigation.component';
import {CardComponent} from './pages/card/card.component';
import {PaginationComponent} from './parts/pagination/pagination.component';
import {AppRoutingModule} from './app-routing.module';
import {LoginComponent} from './pages/login/login.component';
import {SignupComponent} from './pages/signup/signup.component';
import {DetailComponent} from './pages/product-detail/detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {CartComponent} from './pages/cart/cart.component';
import {CookieService} from 'ngx-cookie-service';
import {ErrorInterceptor} from './_interceptors/error-interceptor.service';
import {JwtInterceptor} from './_interceptors/jwt-interceptor.service';
import {OrderComponent} from './pages/order/order.component';
import {OrderDetailComponent} from './pages/order-detail/order-detail.component';
import {ProductListComponent} from './pages/product-list/product.list.component';
import {UserDetailComponent} from './pages/user-edit/user-detail.component';
import {ProductEditComponent} from './pages/product-edit/product-edit.component';
import { SignupSupplierComponent } from './pages/signup-supplier/signup-supplier.component';
import { CreateProductsComponent } from './pages/create-products/create-products.component';
import { CreateProductsCustomerComponent } from './pages/create-products-customer/create-products-customer.component';
import { ProductListCustomerComponent } from './pages/product-list-customer/product-list-customer.component';
import { AdminListComponent } from './pages/admin-list/admin-list.component';
import { UserEditSupplierComponent } from './pages/user-edit-supplier/user-edit-supplier.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { PaymentComponent } from './pages/payment/payment.component';
import {RouterModule} from '@angular/router';
import { CloseComponent } from './pages/close/close.component';


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    CardComponent,
    PaginationComponent,
    LoginComponent,
    SignupComponent,
    DetailComponent,
    CartComponent,
    OrderComponent,
    OrderDetailComponent,
    ProductListComponent,
    UserDetailComponent,
    ProductEditComponent,
    SignupSupplierComponent,
    CreateProductsComponent,
    CreateProductsCustomerComponent,
    ProductListCustomerComponent,
    AdminListComponent,
    UserEditSupplierComponent,
    CheckoutComponent,
    PaymentComponent,
    CloseComponent,


  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        ReactiveFormsModule,

    ],
  providers: [CookieService,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
