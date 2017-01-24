import mongoose = require('mongoose');

var headingConfigSchema = new mongoose.Schema({
    from: String,
    to: String,
    message_type: String    
});

var mcConfigSchema = new mongoose.Schema({
    ip: String,
    port: Number
});

var gcConfigSchema = new mongoose.Schema({
    port: Number,
    vehical_connection: String 
});

var dbConfigSchema = new mongoose.Schema({
    ip: String,
    port: Number
});

var wsConfigSchema = new mongoose.Schema({
    ip: String,
    port: Number
});

var saftyConfigSchema = new mongoose.Schema({
    note: String,
    drone2drone: Number,
    drone2alt: Number,
    drone2bs: Number,
    bs2world: Number  
});

var routesConfigSchema = new mongoose.Schema({
    base_station: String,
    config: String,
    drones: String,
    geo: String,
    mfmonitor: String,
    mission: String,
    tickets: String   
});

var configSchema = new mongoose.Schema({
    name: String, 
    mission_control: mcConfigSchema,
    ground_control: gcConfigSchema, 
    web_server: wsConfigSchema,
    safty: saftyConfigSchema,
    routes: routesConfigSchema,
    mongo: dbConfigSchema
});

var Config = mongoose.model('Config', configSchema);

export = Config;