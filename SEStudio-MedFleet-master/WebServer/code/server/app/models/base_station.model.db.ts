
import mongoose = require('mongoose');
import {Point, Polygon} from './geometry.model.db';

var bsSchema = new mongoose.Schema({
    geometry:       Point,
    flight_zone:    Polygon,
    drones: [String],
    is_a:           {type: String, default: "base_station"}
});

var BaseStation = mongoose.model('BaseStation', bsSchema);

export = BaseStation;

