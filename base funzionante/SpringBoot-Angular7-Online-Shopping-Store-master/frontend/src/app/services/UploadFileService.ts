
import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Client} from '../models/Client';
import {apiUrl} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {

  constructor(private http: HttpClient) { }

  pushFileToStorage(file: File) {  // pushFileToStorage2(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();

    formdata.append('file', file);

    const req = new HttpRequest('POST', 'http://localhost:8080/api/api/file/upload', formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.post<File>('http://localhost:8080/api/api/file/upload', file); // return this.http.request(req);
  }

  pushFileToStorage2(file: File): Observable<any> {
    const url = 'http://localhost:8080/api/api/file/upload';
    return this.http.post<File>(url, file);
  }

  getFiles(): Observable<any> {
    return this.http.get('http://localhost:8080/api/file/all');
  }
}
