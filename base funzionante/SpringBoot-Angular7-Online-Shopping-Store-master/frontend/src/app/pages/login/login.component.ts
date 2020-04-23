import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Role} from '../../enum/Role';
import * as CryptoJS from 'crypto-js';


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

  @ViewChild('loginRef', {static: true}) loginElement: ElementRef;

      public auth2: any;
      public encryptSecretKey: CryptoKey;

    constructor(private userService: UserService,
                private router: Router,
                private route: ActivatedRoute,
                private element: ElementRef) {
    }

    ngOnInit() {
        const params = this.route.snapshot.queryParamMap;
        this.isLogout = params.has('logout');
        this.returnUrl = params.get('returnUrl');
//        this.googleInit();
        this.googleSDK();
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

/*
  public googleInit() {
    const that = this;
    gapi.load('auth2', function() {
      that.auth2 = gapi.auth2.init({
        client_id: '250026481236-1tcpsas73dkp7nlb1kurknvsojrjiem7.apps.googleusercontent.com',
        cookiepolicy: 'single_host_origin',
        scope: [
          'profile',
          'email',
          'https://www.googleapis.com/auth/plus.me',
          'https://www.googleapis.com/auth/contacts.readonly',
          'https://www.googleapis.com/auth/admin.directory.user.readonly'
        ].join(' ')
      });
      that.attachSignin(that.element.nativeElement.firstChild);
//      that.attachSignin(ElementRef);
    });
  }
  public attachSignin(element) {
    const that = this;
    this.auth2.attachClickHandler(element, {},
      // tslint:disable-next-line:only-arrow-functions
      function(googleUser) {

        const profile = googleUser.getBasicProfile();
        console.log('Token || ' +     googleUser.getAuthResponse().id_token);
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        // YOUR CODE HERE


        // tslint:disable-next-line:only-arrow-functions
      }, function(error) {
        console.log(JSON.stringify(error, undefined, 2));
      });
  }
*/

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

    this.auth2.attachClickHandler(this.loginElement.nativeElement, {},
      (googleUser) => {

        const profile = googleUser.getBasicProfile();
        console.log('Token || ' + googleUser.getAuthResponse().id_token);
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        this.userService.getClient(profile.getEmail()).subscribe();

        this.userService.getClient(profile.getEmail()).subscribe( u => {
          this.encryptData(profile.getEmail());
          this.fillLoginFields(u.email, this.encryptSecretKey);

          console.log('Cripted Pass using crypto.getRandomValues(): ' + window.btoa(profile.getEmail()));
        }, e => {
          this.encryptData(profile.getEmail());
          console.log(this.encryptSecretKey);





          console.log('Cripted Pass: error');
        });

        // YOUR CODE HERE
/*
        this.model.username = profile.getEmail();
        this.model.password = '123';
*/
        this.model.googleIdToken = googleUser.getAuthResponse().id_token;
        console.log('su: ' + this.model.googleIdToken);
        this.onSubmit();

      }, (error) => {
        alert(JSON.stringify(error, undefined, 2));
      });

  }

    fillLoginFields(u, p) {
        this.model.username = u;
        this.model.password = p;
        this.onSubmit();
    }


  encryptData(data) {

    try {
      return CryptoJS.AES.encrypt(JSON.stringify(data), this.encryptSecretKey).toString();
    } catch (e) {
      console.log(e);
    }

  }

}
