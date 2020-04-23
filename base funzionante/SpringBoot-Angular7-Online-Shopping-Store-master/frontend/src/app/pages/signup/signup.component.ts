import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Location} from '@angular/common';
import {Client} from '../../models/Client';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  @ViewChild('googleRef', {static: true}) googleRefElement: ElementRef;


  client: Client;

  constructor( private location: Location,
               private userService: UserService,
               private router: Router) {
    this.client = new Client();

  }

  public auth2: any;

  ngOnInit() {
    this.googleSDK();
  }

  onSubmit() {
    this.userService.signUpClient(this.client).subscribe(u => {
      this.router.navigate(['/login']);
    },
        e => {});
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
    }

    (function(d, s, id){
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) {return;}
      js = d.createElement(s); js.id = id;
      js.src = 'https://apis.google.com/js/platform.js?onload=googleSDKLoaded';
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'google-jssdk'));

  }

  prepareLoginButton() {
    this.auth2.attachClickHandler(this.googleRefElement.nativeElement, {},
      (googleUser) => {

        const profile = googleUser.getBasicProfile();
        console.log('Token || ' + googleUser.getAuthResponse().id_token);
        console.log('ID: ' + profile.getId());
        console.log('Name: ' + profile.getName());
        console.log('Image URL: ' + profile.getImageUrl());
        console.log('Email: ' + profile.getEmail());
        // YOUR CODE HERE
        /*
                this.model.username = profile.getEmail();
                this.model.password = '123';
        */
/*
        this.model.googleIdToken = googleUser.getAuthResponse().id_token;
        console.log('su: ' + this.model.googleIdToken);
        this.onSubmit();
*/

      }, (error) => {
        alert(JSON.stringify(error, undefined, 2));
      });

  }

}
