import {config} from './config';
import path = require('path');
var  express = require('express');
var db = require('./mongoose');

//middleware
import morgan       = require('morgan');
import bodyParser   = require('body-parser');
import compression  = require('compression');
var favicon         = require('express-favicon');

//routes 
import bs_routes        = require('../app/routes/base_station.rts');
import config_routes    = require('../app/routes/config.rts');
import drone_routes     = require('../app/routes/drone.rts');
import mission_routes   = require('../app/routes/mission.rts');
import mm_monitor       = require('../app/routes/mm_monitor.rts');
import ticket_routes    = require('../app/routes/ticket.rts');

/// add db config to expres 
var app = express(db); 

// middleware ////////////////////////////////////////////////////////////
if (config.environment == "development") { app.use(morgan('dev')); }    // logger
app.use(compression());                                                 // comperess data 
app.use(bodyParser.urlencoded({ extended: true }));                     // sets file return to right Type 
app.use(bodyParser.json());                                             // json middleware 
                                                                        // server favicon
app.use(favicon(path.resolve(__dirname, '../..', 'public/fav/') + '/favicon.ico')); 

// routes ////////////////////////////////////////////////////////////
//app.use('/basestation', bs_routes);
app.use('/config', config_routes);
app.use('/drones', drone_routes); 
app.use('/missions', mission_routes);
app.use('/', mm_monitor);
app.use('/tickets', ticket_routes);

///////// static folders ///////////////////////////////////////////
app.use('/public',       express.static(path.resolve(__dirname, '../..',    'public')));
app.use(                 express.static(path.resolve(__dirname, '../..',    'client')));
app.use('/node_modules', express.static(path.resolve(__dirname, '../../..', 'node_modules')));

///////// export configured express ///////////////////////////////
export = app; 