import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../../environments/environment';

@Component({
  selector: 'app-api',
  templateUrl: './api.component.html',
  styleUrls: ['./api.component.css']
})
export class ApiComponent implements OnInit {

  constructor( private httpClient: HttpClient) { }

  ngOnInit() {
    this.httpClient.post( `${apiUrl}/producto/api`, { observe: 'response' })
      .subscribe((response) => {
        });
  }
}
