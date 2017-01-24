//////////////// set env
process.env.NODE_ENV = process.env.NODE_ENV || 'development';
import { config } from './config/config';
require('pretty-error').start(); // make errors easy to read


//////////////// Express , Http Server , Socket IO, db is added in express config
var app = require('./config/express');
import http = require('http');
import socketio = require('socket.io');

var server = http.createServer(app);  // server eats the app
var io = socketio.listen(server);     // io eats the server 



///////////////////// Start Server 
server.listen(config.express.port);
console.log(`Server running on ${config.express.port}`);




/////////////////////////////// need to refector to sockdet io config file......


/// importing the data to send to clinet
import mongoose = require('mongoose');
var Ticket = mongoose.model('Ticket');
var Drone = mongoose.model('Drone');

/// SOCKET IO CONFIGURATION ///////
io.on('connection', function (socket) {
  console.log('SOCKETIO: a user connected');
  socket.on('get tickets', function (msg) {
    Ticket.find(function (err: any, tickets: any) {
      if (err) { console.log("Error"); }
      else {
        console.log(tickets);
        io.emit('tickets', tickets);
      }
    });
  });
  socket.on('get drones', function (msg) {
    Drone.find(function (err: any, drones: any) {
      if (err) { console.log("Error"); }
      else {
        console.log(drones);
        io.emit('drones', drones);
      }
    });
  });
  socket.on('disconnect', function () {
    console.log('SOCKETIO: user disconnected');
  });
});

///////////////////// Export App 
export = app;