import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {Client} from '../../models/Client';
import {Router} from '@angular/router';
import {Observable, Subject} from "rxjs";
import {Role} from '../../enum/Role';

@Component({
    selector: 'app-user-detail',
    templateUrl: './user-detail.component.html',
    styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {




    constructor(private userService: UserService,
                private router: Router) {
    }

    client = new Client();


    ngOnInit() {
        const account = this.userService.currentUserValue.account;

        this.userService.getClient(account).subscribe( u => {
            this.client = u;
            this.client.password = '';
        }, e => {

        });
    }

    onSubmit() {
        this.userService.updateClient(this.client).subscribe(u => {
            this.userService.nameTerms.next(u.name);
            let url = '/';
            if (this.client.role != Role.Customer) {
                url = '/seller';
            }
            this.router.navigateByUrl(url);
        }, _ => {})
    }

}
