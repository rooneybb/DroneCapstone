import { Component } from '@angular/core';
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from '@angular/router-deprecated';

//providers 
import {HTTP_PROVIDERS} from '@angular/http';
import 'rxjs/add/operator/map';

// services 
import { TicketService  } from './service/ticket.service';
import { DroneService   } from './service/drone.service';
import { GMapAPILoader  } from './service/map/map.service';
import { MapFactory     } from './service/map/map.wrapper';
import { MissionService } from './service/map/mission.service';

// routes
import { TicketListComponent    } from './ticket-list/ticket-list.component';
import { DroneListComponent     } from './drone-list/drone-list.component';
import { DashboardComponent     } from './dashboard/dashboard.component';
import { MapMakerComponent      } from './map/map.component';

@Component({
  selector: 'my-app',
  templateUrl: 'app/app.html',
  directives: [ROUTER_DIRECTIVES],
  providers: [  HTTP_PROVIDERS, 
                ROUTER_PROVIDERS,  
                DroneService, 
                TicketService,
                GMapAPILoader, 
                MapFactory,
                MissionService
             ]
})

@RouteConfig([
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: DashboardComponent,
        useAsDefault: true
    },
    {
        path: '/droneslist',
        name: 'DronesList',
        component: DroneListComponent
    },
    {
        path: '/ticketlist',
        name: 'TicketList',
        component: TicketListComponent
    },
    {
        path: '/map',
        name: 'Map',
        component: MapMakerComponent
    }
])

export class MedFleetMonitor { }