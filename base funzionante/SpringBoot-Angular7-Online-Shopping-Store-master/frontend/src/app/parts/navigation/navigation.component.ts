import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {Subscription} from 'rxjs';
import {JwtResponse} from '../../response/JwtResponse';
import {Router} from '@angular/router';
import {Role} from '../../enum/Role';

@Component({
    selector: 'app-navigation',
    templateUrl: './navigation.component.html',
    styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit, OnDestroy {


    currentUserSubscription: Subscription;
    name$;
    name: string;
    currentUser: JwtResponse;
    root = '/';
    Role = Role;

    constructor(private userService: UserService,
                private router: Router,
    ) {

    }


  ngOnInit() {
    this.name$ = this.userService.name$.subscribe(aName => this.name = aName);
    this.currentUserSubscription = this.userService.currentUser.subscribe(client => {
      this.currentUser = client;

      if (!client || client.role === Role.Customer || client.role === Role.Manager) {
        this.root = '/';
      } else {
        this.root = '/seller';
      }
    });
  }
   /* ngOnInit() {
        this.name$ = this.userService.name$.subscribe(aName => this.name = aName);
        this.currentUserSubscription = this.userService.currentUser.subscribe(user => {
            this.currentUser = user;

            if (!user || user.role == Role.Customer || user.role == Role.Client) {
                this.root = '/';
            } else {
                this.root = '/seller';
            }
        });
    }*/

    ngOnDestroy(): void {
        this.currentUserSubscription.unsubscribe();
        // this.name$.unsubscribe();
    }

    logout() {
        this.userService.logout();
        // this.router.navigate(['/login'], {queryParams: {logout: 'true'}} );
    }

}
