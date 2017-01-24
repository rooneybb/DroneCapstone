import { Component, OnInit }  from '@angular/core';

import { TicketService }      from '../service/ticket.service';
import { TicketModel   }      from '../models/ticket.model';

@Component({
  selector: 'ticket-list',
  templateUrl: 'app/ticket-list/ticket-list.html'
})

export class TicketListComponent implements OnInit {   
  tickets: TicketModel[];
  errorMessage: string;
  socket = null;

  constructor(private _ticketservice: TicketService) { }
  
  ngOnInit() { 
   this.getTickets(); 
  }
  
  getTickets() {
    this._ticketservice.getTickets()
      .subscribe(
          tickets => this.tickets = tickets,
          error   => this.errorMessage);
  }
}