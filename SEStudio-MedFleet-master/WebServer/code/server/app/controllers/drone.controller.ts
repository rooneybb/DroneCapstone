//////////////// db / express
import express  = require("express");
import mongoose = require('mongoose');

//////////////// import drone model
var Drone = mongoose.model('Drone');

//////////////// functions

// list all tickets in json format
export function list(req: express.Request, res: express.Response) {
    Drone.find(function(err : any, drones: any){
        if (err) { console.log(err); } 
        else     { res.json(drones); }
    });
    
};

// create new tickets from json object in body of request
export function create(req: express.Request, res: express.Response) {
    var drone = new Drone(req.body);
	drone.save(function(err: any) {
		if (err) { console.log(err); } 
        else {res.json(drone);}
	});
};

// update a ticket via json object in body and id in the url 
export function update(req: express.Request, res: express.Response) {
    var drone = req.body;
    Drone.findByIdAndUpdate(req.params.droneid,drone, 
        function(err, drone) {
            if (err) { console.log(err); } 
            else {res.json(drone);}
        })
};

// returns one ticket via the id in the url
export function getOne(req: express.Request, res: express.Response) {
    Drone.findById(req.params.droneid, 
        function(err, drone) {
            if (err) { console.log(err); } 
            else {res.json(drone);}
    })
};

// deletes the ticket with the id in the url
/// todo create with call back of status 
//query.remove({ name: 'Anne Murray' }).remove(callback)
export function delete_one(req: express.Request, res: express.Response) {
    var drone = req.body;
    Drone.findByIdAndRemove(req.params.droneid,drone, 
        function(err, status) {
            if (err) { console.log(err); } 
            else {res.json(status);}
        })
};

// deletes all the drone objects
export function delete_all(req: express.Request, res: express.Response) {
    Drone.remove(
        function(err, status) {
            if (err) { res.send("phawker"); } 
            else {res.json(status);}
        })
};


