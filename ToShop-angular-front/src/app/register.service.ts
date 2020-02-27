import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  private baseUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:ban-types
  registerUser(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}` + `/register`, user);
  }


}
