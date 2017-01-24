import mongoose = require('mongoose');

var droneSchema = new mongoose.Schema({

    latitude:       {type: Number, max: 180, min: -180, default: null},
    longitude:      {type: Number, max: 180, min: -180, default: null},
    altitude:       {type: Number, min: 0, default: null},
    local_location: {type: String, default: null},
    gps_strength:   {type: String, default: null},
    pitch_axis:     {type: Number, default: null},
    roll_axis:      {type: Number, default: null},
    yaw_axis:       {type: Number, default: null},
    battery_voltage: {type: Number, default: null},
    battery_percentage: {type: Number, min: 0, max: 100, default: null},
    last_heartbeat: {type: Number, default: null},
    heading:        {type: Number, default: null},
    armed:          {type: Boolean, default: null},
    mode:           {type: String, default: null},
    system_status:  {type: String, default: null},
    ground_speed:   {type: Number, min: 0, default: null},
    air_speed:      {type: Number, min: 0, default: null},
    is_a:           {type: String, default: "drone"}
});

var Drone = mongoose.model('Drone', droneSchema);

export = Drone;