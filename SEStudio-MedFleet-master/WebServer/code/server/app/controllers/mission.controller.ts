//////////////// db / express
import express      = require("express");
import mongoose     = require('mongoose');
import bodyParser   = require('body-parser');

//////////////// import mission model
var Mission = mongoose.model('Mission');

//////////////// functions

// list all missions in json format
export function list(req: express.Request, res: express.Response) {
    Mission.find(function(err : any, missions: any){
        if (err) { console.log(err); } 
        else     { res.json(missions); }
    });
    
};

// create new mission from json object in body of request
export function create(req: express.Request, res: express.Response) {
    var mission = new Mission(req.body);
    
	mission.save(function(err: any) {
		if (err) { console.log(err); } 
        else {res.json(mission);}
	});
};

// update a mission via json object in body and id in the url 
export function update(req: express.Request, res: express.Response) {
    var mission = req.body;
    Mission.findByIdAndUpdate(req.params.missionid,mission, 
        function(err, mission) {
            if (err) { console.log(err); } 
            else {res.json(mission);}
        })
};

// returns one mission via the id in the url
export function getOne(req: express.Request, res: express.Response) {
    Mission.findById(req.params.missionid, 
        function(err, mission) {
            if (err) { console.log(err); } 
            else {res.json(mission);}
    })
};

// deletes the mission with the id in the url
/// todo create with call back of status 
//query.remove({ name: 'Anne Murray' }).remove(callback)
export function delete_one(req: express.Request, res: express.Response) {
    var mission = req.body;
    Mission.findByIdAndRemove(req.params.missionid,mission, 
        function(err, status) {
            if (err) { console.log(err); } 
            else {res.json(status);}
        })
};

// deletes all the mission objects
export function delete_all(req: express.Request, res: express.Response) {
    Mission.remove(
        function(err, status) {
            if (err) { res.send("phawker"); } 
            else {res.json(status);}
        })
};


// update a mission via json object in body and id in the url 
export function update_flt_paths(req: express.Request, res: express.Response) {
    var fltpths = req.body;
    console.log(req.body); 
    Mission.findByIdAndUpdate(req.params.missionid, 
    {  
        $set: { "flight_paths" : fltpths }
    },
    function(err, m) {
        if (err) { console.log(err); } 
        else { res.json(m); }
    })
};

// returns one mission via the id in the url
export function getOnebyDroneId(req: express.Request, res: express.Response) {
    Mission.findOne({ "drone_id" : req.params.droneid}, 
        function(err, mission) {
            if (err) { console.log(err); } 
            else {res.json(mission);}
    })
};

export function updatebyDroneId(req: express.Request, res: express.Response) {
    var mission = req.body;
    Mission.update({ "drone_id" : req.params.droneid} ,mission, 
        function(err, mission) {
            if (err) { console.log(err); } 
            else {res.json(mission);}
        })
};