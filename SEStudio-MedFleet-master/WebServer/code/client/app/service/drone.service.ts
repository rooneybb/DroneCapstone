import { Injectable }   from '@angular/core';
import { Http, Response } from '@angular/http';

import { DroneModel }  from '../models/drone.model';
import { Observable }     from 'rxjs/Observable';


@Injectable()
export class DroneService {
    private drones: DroneModel[];
    private droneUrl = '/drones';
    
    constructor(private http: Http) { }

    getDrones(): Observable<DroneModel[]> {
        return Observable.interval(3000)
            .flatMap( () => this.http.get(this.droneUrl))
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
