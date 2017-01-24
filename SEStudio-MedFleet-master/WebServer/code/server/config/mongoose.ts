// Load the module dependencies
import {config} from './config';
import mongoose = require('mongoose');

// Load the application models
require('../app/models/drone.model.db');
require('../app/models/ticket.model.db');

// load the db
var db = mongoose.connect(config.db.connection);
    
export = db;
