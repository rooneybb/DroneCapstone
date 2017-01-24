import {Component}  from '@angular/core';

import {TicketListComponent} from '../ticket-list/ticket-list.component';
import {DroneListComponent}  from '../drone-list/drone-list.component';
import {MapMakerComponent}   from '../map/map.component';

@Component({
  selector: 'dashboard',
  templateUrl: 'app/dashboard/dashboard.html',
  directives: [MapMakerComponent, TicketListComponent, DroneListComponent]
})

export class DashboardComponent {}
