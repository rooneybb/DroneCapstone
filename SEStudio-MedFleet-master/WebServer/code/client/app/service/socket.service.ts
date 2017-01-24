import {Injectable, OnInit } from '@angular/core';

import { DroneModel     } from '../models/drone.model';
import { TicketModel    } from '../models/ticket.model';


@Injectable()
export class SocketIoService implements OnInit {
    socket: SocketIOClient.Socket;
    drones: DroneModel[];
    tickets: TicketModel[];

    constructor() {
        // this.socket = io();
        // this.socket.on('tickets', function (tx) {
        //     console.log("got hooked up with tixkets" + tx);
        // });
        // this.socket.on('drones', function (drones) {
        //     console.log('got hooked up with some drones' + drones);
        // });
    }

    ngOnInit() { }

    getTickets() {
       // this.socket.emit('get tickets', 'hook me up');
    }
    getDrones() {
        //this.socket.emit('get drones', 'hook me up');
    }
}