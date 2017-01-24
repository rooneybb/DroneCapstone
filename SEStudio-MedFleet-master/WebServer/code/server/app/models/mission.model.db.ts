import mongoose = require('mongoose');
import {MultiLineString} from './geometry.model.db';

var missionSchema = new mongoose.Schema({
   drone_id: String,
   tickets: [String],
   flight_paths: [mongoose.Schema.Types.Mixed],
   is_a: {type: String, default: "mission"}  
});

var Mission = mongoose.model('Mission', missionSchema);

export = Mission;

