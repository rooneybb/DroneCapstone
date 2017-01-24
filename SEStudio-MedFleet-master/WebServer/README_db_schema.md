# DataBase Schema
The Database schema hold the defination for all objects in the system. These Models are used by each model and is used to pass all data between modules. 

##Base Station
* **geometry**: Point,
* **flight_zone**: Polygon,
* **drones**: array of drones _id's String

##Config
    name: String, 
    mission_control: {
        ip: String,
        port: Number
    },
    ground_control: {
        port: Number,
        vehical_connection: String 
    }, 
    web_server: {
        ip: String,
        port: Number
    },
    safty: {
        note: String,
        drone2drone: Number,
        drone2alt: Number,
        drone2bs: Number,
        bs2world: Number  
    },
    routes: {
        note: String,
        drone2drone: Number,
        drone2alt: Number,
        drone2bs: Number,
        bs2world: Number  
    },
    mongo: {
        ip: String,
        port: Number
    }

##Drones
* **latitude**: type: Number, max: 180, min: -180, default: null,
* **longitude**: type: Number, max: 180, min: -180, default: null,
* **altitude**: type: Number, min: 0, default: null,
* **local_location**: type: String, default: null,
* **gps_strength**: type: String, default: null,
* **pitch_axis**: type: Number, default: null,
* **roll_axis**: type: Number, default: null,
* **yaw_axis**: type: Number, default: null,
* **battery_voltage**: type: Number, default: null,
* **battery_percentage**: type: Number, min: 0, max: 100, default: null,
* **last_heartbeat**: type: Number, default: null,
* **heading**: type: Number, default: null,
* **armed**: type: Boolean, default: null,
* **mode**: type: String, default: null,
* **system_status**: type: String, default: null,
* **ground_speed**: type: Number, min: 0, default: null,
* **air_speed**: type: Number, min: 0, default: null

##Geometry see [full defination of geojson](http://geojson.org/geojson-spec.html) for the full defination 
**Always list coordinates in longitude, latitude order.**
* **Point** 
    - type: "Point"
    - coordinates: Array of two doubles [longitude, latitude]

* **LineString** 
    - "type": "LineString"
    - coordinates:  array of array of two doubles [longitude, latitude]

* **Polygon**
    - type: "Polygon"
    - coordinates:  array of array of two doubles [longitude, latitude] that first and last points are the same

* **MultiPoint** 
    - type: "MultiPoint"
    - coordinates:  Array of Points 

* **MultiLineString**  
    - type: "MultiLineString"
    - coordinates:  Array of Line Strings


* **MultiPolygon**  
    - type: "MultiPolygon",
    - coordinates:  Array of polygons 

##Missions
* **drone_id**: String,
* **tickets**: array of Strings,
* **flight_paths**: array of LineString (MultiLineString) 

##Tickets
* **latitude**:    type: Number, min: -180, max: 180 ,
* **longitude**:   type: Number, min: -180, max: 180 ,
* **altitude**:    type: Number, min: 0, default: 0,
* **urgency**:     type: Number, min: 0, max: 5, default: 0,
* **status**:      type: String, enum: status_enum, default: 'received',
* **received**:    type: Date, default: Date.now, expires: '24h' ,
* **completed**:   type: String, default: null 

