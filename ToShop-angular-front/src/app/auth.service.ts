import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';


/*
To access the Spring Boot RESTful API from Angular 8 application,
we have to create services for that.
*/
const apiUrl = 'http://localhost:8080/api/auth';

// @ts-ignore
@Injectable({
  providedIn: 'root'
})

// Declare a constant variable as Spring Boot REST API URL after the imports.


export class AuthService {

  // const apiUrl = 'http://localhost:8080/api/auth';

  isLoggedIn = false;
  redirectUrl: string;

  constructor(private http: HttpClient) { }

  // create all required functions for Login, Logout, Register, and helper functions

  login(data: any): Observable<any> {
    return this.http.post<any>(apiUrl + 'login', data)
      .pipe(
        tap(_ => this.isLoggedIn = true),
        catchError(this.handleError('login', []))
      );
  }

  logout(): Observable<any> {
    return this.http.get<any>(apiUrl + 'signout')
      .pipe(
        tap(_ => this.isLoggedIn = false),
        catchError(this.handleError('logout', []))
      );
  }

  register(data: any): Observable<any> {
    return this.http.post<any>(apiUrl + 'register', data)
      .pipe(
        tap(_ => this.log('login')),
        catchError(this.handleError('login', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }

}
