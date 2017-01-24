import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable }     from 'rxjs/Observable';

import { TicketModel } from '../models/ticket.model';


@Injectable()
export class TicketService {
    constructor(private http: Http) { }
    
    private ticketUrl = '/tickets';

    getTickets(): Observable<TicketModel[]> {
        return Observable.interval(3000)
            .flatMap( () => this.http.get(this.ticketUrl))
            .map(this.extractData)
            .catch(this.handleError);
    }
    
    private extractData(res: Response) {
        if (res.status < 200 || res.status >= 300) {
            throw new Error('Response status: ' + res.status);
        }
        let body = res.json();
        return body || {};
    }
    private handleError(error: any) {
        let errMsg = error.message || 'Server error';
        console.error(errMsg); // log to console
        return Observable.throw(errMsg);
    }
 }