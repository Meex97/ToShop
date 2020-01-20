import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserController {

  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getCustomersList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
