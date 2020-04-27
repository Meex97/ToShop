import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Role} from '../../enum/Role';
import * as CryptoJS from 'crypto-js';
import {Client} from '../../models/Client';
import {delay} from 'rxjs/operators';


declare const gapi: any;

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    isInvalid: boolean;
    isLogout: boolean;
    submitted = false;
    model: any = {
        username: '',
        password: '',
        googleIdToken: null,
        remembered: false
    };


    returnUrl = '/';

  encryptMode: boolean;
  textToConvert: string;
  password: string;
  conversionOutput: string;
  customer: Client;


  @ViewChild('loginRef', {static: true}) loginElement: ElementRef;

      public auth2: any;
      public encryptSecretKey: CryptoKey;
      private profile: gapi.auth2.BasicProfile;
      private x: boolean;
      private done: boolean;
      private counter: number;

    constructor(private userService: UserService,
                private router: Router,
                private route: ActivatedRoute,
                private element: ElementRef) {

      this.encryptMode = true;
    }

    ngOnInit() {
        const params = this.route.snapshot.queryParamMap;
        this.isLogout = params.has('logout');
        this.returnUrl = params.get('returnUrl');
//        this.googleInit();
        this.googleSDK();

        this.customer = new Client();
        this.x = true;
        this.done = false;
    }

    onSubmit() {
        this.submitted = true;
        this.userService.login(this.model).subscribe(
            client => {
              if (client) {
                if (client.role === Role.Employee) {
                    this.returnUrl = '/seller';
                }
                this.router.navigateByUrl(this.returnUrl);
              } else {
                    this.isLogout = false;
                    this.isInvalid = true;
              }

            }
        );
    }


  googleSDK() {
    window['googleSDKLoaded'] = () => {
      window['gapi'].load('auth2', () => {
        this.auth2 = window['gapi'].auth2.init({
          client_id: '250026481236-1tcpsas73dkp7nlb1kurknvsojrjiem7.apps.googleusercontent.com',
          cookiepolicy: 'single_host_origin',
          scope: 'profile email'
        });
        this.prepareLoginButton();
      });
    };

    (function(d, s, id) {
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) {return;}
      js = d.createElement(s); js.id = id;
      js.src = 'https://apis.google.com/js/platform.js?onload=googleSDKLoaded';
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'google-jssdk'));

  }


  prepareLoginButton() {

    this.auth2.attachClickHandler(this.loginElement.nativeElement, {}, (googleUser) => {
      this.profile = googleUser.getBasicProfile();
      this.model.googleIdToken = googleUser.getAuthResponse().id_token;
      this.done = true;

    });

  }

    fillLoginFields(u, p) {
        this.model.username = u;
        this.model.password = p;
        this.onSubmit();
    }


  encryptData(data) {
    if (data.trim() === '' ) {
      this.conversionOutput = 'Please fill the textboxes.';
      return;
    } else {
      if (this.encryptMode) {
        this.conversionOutput = CryptoJS.AES.encrypt(data.trim(), data.trim()).toString();
      } else {
        this.conversionOutput = CryptoJS.AES.decrypt(data.trim(), data.trim()).toString(CryptoJS.enc.Utf8);
      }
    }
  }


  private isRegistered() {
    this.userService.getClient(this.profile.getEmail()).subscribe( u => {
      // this.encryptData(profile.getEmail());
      console.log('login');
    }, e => {
      this.x = false;
      console.log('non cè mica');
      // this.encryptData(this.profile.getEmail());
      //  console.log(this.conversionOutput);
    });
  }

  ok() {
    (async () => {

      this.counter = 0;
      while (!this.done) {
        await this.delay(100);
        this.counter++;
        console.log(this.counter);
      }

      console.log('USCITOOOOOOOO');

      this.isRegistered();


      if (!this.x) {
        console.log('creo nuovo client_____________________________');
        this.customer.createClient(this.profile.getEmail(), this.profile.getGivenName(),
          this.profile.getFamilyName(), this.profile.getEmail());
        console.log(this.customer.name);

        this.userService.signUpClient(this.customer).subscribe(u => {
            console.log('singup');
          },
          e => {});
      }
      this.fillLoginFields(this.profile.getEmail(), this.profile.getEmail());

    })();
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }


}
