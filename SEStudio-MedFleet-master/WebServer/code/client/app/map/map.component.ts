import { Component      } from '@angular/core';

/// map classes
import { GMapAPILoader  } from '../service/map/map.service'
import { MapOptions     } from '../service/map/map.options';
import { MapFactory     } from '../service/map/map.wrapper';

// services
import { DroneService   } from '../service/drone.service';
import { TicketService  } from '../service/ticket.service';
import { MissionService } from '../service/map/mission.service';

// models 
import { DroneModel     } from '../models/drone.model';
import { TicketModel    } from '../models/ticket.model';
import { Mission        } from '../models/missions.model';



@Component({
  selector: 'mapmaker',
  templateUrl: '/app/map/map.html'
})

export class MapMakerComponent {
  errorMessage: string;
  lat: number = 41.87;
  lng: number = -87.63;
  zoom: number = 8;

  constructor(private _droneservice: DroneService,
              private _ticketservice: TicketService,
              private _mapsWrapper: GMapAPILoader,
              private _shapefactory: MapFactory,
              private _missionservice: MissionService
  ) { }

  ngOnInit() {
    const container = document.getElementById('gmap');
    this.initMapInstance(container);
    this.getDrones();
    this.getTickets();
    this.getMissions();
  }

  getDrones() {
    this._droneservice.getDrones()
      .subscribe(
        drones => this._shapefactory.DronesToGeo(drones),
        error => this.errorMessage = <any>error);
  }

  getTickets() {
    this._ticketservice.getTickets()
      .subscribe(
        tickets => this._shapefactory.TicketsToGeo(tickets),
        error => this.errorMessage);
  }
  
    getMissions() {
    this._missionservice.getMissions()
      .subscribe(
        missions => this._shapefactory.flightPathsToGeo(missions),
        error => this.errorMessage);
  }

  initMapInstance(el: HTMLElement) {
    this._mapsWrapper.createMap(el, MapOptions);
  }
}
