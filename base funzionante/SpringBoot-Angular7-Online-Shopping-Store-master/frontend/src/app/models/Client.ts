import {Role} from "../enum/Role";

export class Client {

    email: string;

    password: string;

    name: string;

    surname: string;

    phone: string;

    address: string;

    active: boolean;

    role: string;

    credits: number;

    constructor(){
        this.active = true;
        this.role = 'ROLE_CLIENT';
        this.credits = 0;
    }
}
