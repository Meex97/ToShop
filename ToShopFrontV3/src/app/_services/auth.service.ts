// This service sends signup, login HTTP POST requests to back-end.

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  /*register(user): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }*/

  registerClient(client): Observable<any> {
    return this.http.post(AUTH_API + 'signupClient', {
      name: client.name,
      surname: client.surname,
      username: client.username,
      email: client.email,
      password: client.password,
      phone: client.phoneNumber,
      address: client.address
    }, httpOptions);
  }

  registerSupplier(supplier): Observable<any> {
    return this.http.post(AUTH_API + 'signupSupplier', {
      name: supplier.name,
      surname: supplier.surname,
      username: supplier.username,
      email: supplier.email,
      password: supplier.password,
      phone: supplier.phoneNumber,
      pIVA: supplier.pIVA,
      address: supplier.address,
      shopName: supplier.shopName
    }, httpOptions);
  }


}
