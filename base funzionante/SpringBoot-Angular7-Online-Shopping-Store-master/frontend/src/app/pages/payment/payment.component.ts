import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {FileUploader} from 'ng2-file-upload';
import {apiUrl} from '../../../environments/environment';
import {ProductInfo} from '../../models/productInfo';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  // @ViewChild('fileInput') fileInput: ElementRef;
  @ViewChild('fileInput', { static: false }) fileInput: ElementRef;
  uploader: FileUploader;
  isDropOver: boolean;

  ngOnInit(): void {
    const headers = [{name: 'Accept', value: 'application/json'}];
    // const url = `${apiUrl}/seller/product/${productInfo.productId}/delete`;
    this.uploader = new FileUploader({url: `${apiUrl}/api/files`, autoUpload: true, headers});

    this.uploader.onCompleteAll = () => alert('File uploaded');

    // const url = `${apiUrl}/files`;
    // return this.http.post<ProductInfo>(url, productInfo);
  }

  fileOverAnother(e: any): void {
    this.isDropOver = e;
  }

  fileClicked() {
    this.fileInput.nativeElement.click();
  }
}
