import {Component, OnInit}  from '@angular/core';

import { DroneService }     from '../service/drone.service';
import { DroneModel }       from '../models/drone.model';

@Component({
  selector: 'drone-list',
  templateUrl: 'app/drone-list/drone-list.html'
})

export class DroneListComponent implements OnInit {   
  public drones: DroneModel[];
  errorMessage: string;

  constructor(private _droneservice:DroneService) { }
  
  ngOnInit() { 
   this.getDrones(); 
  }
  
  getDrones() {
    this._droneservice.getDrones()
      .subscribe(
          drones => this.drones = drones,
          error => this.errorMessage = <any>error);
  }
  
}
 