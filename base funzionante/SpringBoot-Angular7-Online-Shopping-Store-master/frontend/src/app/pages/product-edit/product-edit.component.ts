import {AfterContentChecked, Component, OnInit} from '@angular/core';
import {ProductInfo} from '../../models/productInfo';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {JwtResponse} from '../../response/JwtResponse';
import {UserService} from '../../services/user.service';
import {HttpClient} from '@angular/common/http';

@Component({
    selector: 'app-product-edit',
    templateUrl: './product-edit.component.html',
    styleUrls: ['./product-edit.component.css']
})
export class ProductEditComponent implements OnInit, AfterContentChecked {

    product = new ProductInfo();

    selectedFile: File;
    message: string;
    model: any = {
      username: '',
      password: '',
      googleIdToken: null,
      remembered: false
    };

   page: Array<ProductInfo>;

    constructor(private productService: ProductService,
                private route: ActivatedRoute,
                private userService: UserService,
                private router: Router,
                private httpClient: HttpClient) {
    }

    productId: string;
    isEdit = false;

    ngOnInit() {
        this.productId = this.route.snapshot.paramMap.get('id');
        if (this.productId) {
            this.isEdit = true;
            this.productService.getDetail(this.productId).subscribe(prod => {
              this.product = prod;
              prod.productimage = 'data:image/jpeg;base64,' + prod.productimage;
              console.log(prod.productimage);
            });
        }
    }

    update() {
        this.productService.update(this.product).subscribe(prod => {
              if (!prod) throw new Error();
              this.router.navigate(['/seller']);
            },
            err => {
            });
        this.onUpload();

    }

    onSubmit() {

      this.update();
    }

    add() {
      this.productService.create(this.product).subscribe(prod => {
                if (!prod) { throw new Error; }
                this.router.navigate(['/']);
            },
            e => {
            });
    }

    ngAfterContentChecked(): void {
        console.log(this.product);
    }


  public onFileChanged(event) {
    // Select File
    this.selectedFile = event.target.files[0];
  }

  onUpload() {

    this.userService.logout();

    // FormData API provides methods and properties to allow us easily prepare form data to be sent with POST HTTP requests.
    const uploadImageData = new FormData();
    uploadImageData.append('image', this.selectedFile, this.product.productId);

    this.httpClient.post('http://localhost:8080/api/image/upload', uploadImageData, { observe: 'response' })
      .subscribe((response) => {
          if (response.status === 200) {
            this.message = 'Image uploaded successfully';
          } else {
            this.message = 'Image not uploaded successfully';
          }
        }
      );

    this.model.username = this.userService.idUtente;
    this.model.password = this.userService.pwsUtente;
    this.userService.login(this.model).subscribe(client => {
      console.log(client);
      this.router.navigate(['/seller']);
    });
  }
}
