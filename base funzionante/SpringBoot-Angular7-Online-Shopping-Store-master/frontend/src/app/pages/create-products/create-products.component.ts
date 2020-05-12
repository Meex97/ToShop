import { Component, OnInit } from '@angular/core';

import {Location} from '@angular/common';

import {ActivatedRoute, Router} from '@angular/router';
import {ProductInfo} from '../../models/productInfo';
import {ProductService} from '../../services/product.service';
import {Observable} from 'rxjs';
import {apiUrl} from '../../../environments/environment';
import {JwtResponse} from '../../response/JwtResponse';
import {UserService} from '../../services/user.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-create-products',
  templateUrl: './create-products.component.html',
  styleUrls: ['./create-products.component.css']
})
export class CreateProductsComponent implements OnInit {


  selectedFile: File;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message: string;
  imageName: any;

  product: ProductInfo;
  private currentUser: JwtResponse;

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router,
              private userService: UserService,
              private httpClient: HttpClient) {
    this.product = new ProductInfo();
  }

  productId: string;

  ngOnInit() {

    this.userService.currentUser.subscribe(supplier => {
      this.currentUser = supplier;
    });
    this.product.idUtente = this.currentUser.id;
    this.product.nameUtente = this.currentUser.name;
  }

  onSubmit() {
    this.product.productStatus = 0;
    this.add();
  }


  add() {
    this.product.type = 1;
    this.productService.create/*ProductSupplier*/(this.product).subscribe(prod => {

        this.router.navigate(['/seller']);
      },
      e => {});
  }




  // Gets called when the user selects an image
  public onFileChanged(event) {
    // Select File
    this.selectedFile = event.target.files[0];
  }
  // Gets called when the user clicks on submit to upload the image
  onUpload() {
    (async () => {

      // FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
      const uploadImageData = new FormData();
      uploadImageData.append('imageFile', this.selectedFile, this.selectedFile.name);

      // Make a call to the Spring Boot Application to save the image
      this.httpClient.post('http://localhost:8080/api/image/upload', uploadImageData, { observe: 'response' })
        .subscribe((response) => {
            if (response.status === 200) {
              this.message = 'Image uploaded successfully';
            } else {
              this.message = 'Image not uploaded successfully';
            }
          }
        );

    })();
    // console.log(this.selectedFile);
  }



  // Gets called when the user clicks on retieve image button to get the image from back end
  getImage() {
    // Make a call to Sprinf Boot to get the Image Bytes.
    this.httpClient.get('http://localhost:8080/api/image/get/' + this.imageName)
      .subscribe(
        res => {
          this.retrieveResonse = res;
          this.base64Data = this.retrieveResonse.picByte;
          this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        }
      );
  }

}
