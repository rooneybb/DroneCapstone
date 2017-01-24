import mongoose = require('mongoose');

export var Point = new mongoose.Schema({
    "type": {type: String, default: "Point"},
    coordinates: [Number]
});

export var LineString = new mongoose.Schema({
    "type": {type: String, default: "LineString"},
    coordinates:  [mongoose.Schema.Types.Mixed]
});

export var Polygon = new mongoose.Schema({
    "type": {type: String, default: "Polygon"},
    coordinates:  [mongoose.Schema.Types.Mixed]
});
export var MultiPoint = new mongoose.Schema({
    "type": {type: String, default: "MultiPoint"},
    coordinates:  [mongoose.Schema.Types.Mixed] 
});
export var MultiLineString = new mongoose.Schema({
    "type": {type: String, default: "MultiLineString"},
    coordinates:  [mongoose.Schema.Types.Mixed]
});

export var MultiPolygon = new mongoose.Schema({
    "type": {type: String, default: "MultiPolygon"},
    coordinates:  [mongoose.Schema.Types.Mixed]
});