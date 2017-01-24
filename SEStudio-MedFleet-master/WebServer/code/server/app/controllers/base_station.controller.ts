//////////////// db / express
import express  = require("express");
import mongoose = require('mongoose');

//////////////// import BaseStation model
var BaseStation = mongoose.model('BaseStation');

//////////////// functions

// list all BaseStation in json format
export function list(req: express.Request, res: express.Response) {
    BaseStation.find(function(err : any, basestation: any){
        if (err) { console.log(err); } 
        else     { res.json(basestation); }
    }); 
};

// create new basestation from json object in body of request
export function create(req: express.Request, res: express.Response) {
    var basestation = new BaseStation(req.body);
    
	basestation.save(function(err: any) {
		if (err) { console.log(err); } 
        else {res.json(basestation);}
	});
};

// update a basestation via json object in body and id in the url 
export function update(req: express.Request, res: express.Response) {
    var basestation = req.body;
    BaseStation.findByIdAndUpdate(req.params.bsid, basestation, 
        function(err, basestation) {
            if (err) { console.log(err); } 
            else {res.json(basestation);}
        })
};

// returns one basestation via the id in the url
export function getOne(req: express.Request, res: express.Response) {
    BaseStation.findById(req.params.bsid, 
        function(err, basestation) {
            if (err) { console.log(err); } 
            else {res.json(basestation);}
    })
};

// deletes the basestation with the id in the url
/// todo create with call back of status 
//query.remove({ name: 'Anne Murray' }).remove(callback)
export function delete_one(req: express.Request, res: express.Response) {
    var basestation = req.body;
    BaseStation.findByIdAndRemove(req.params.bsid,basestation, 
        function(err, status) {
            if (err) { console.log(err); } 
            else {res.json(status);}
        })
};

// deletes all the basestation objects
export function delete_all(req: express.Request, res: express.Response) {
    BaseStation.remove(
        function(err, status) {
            if (err) { res.send("phawker"); } 
            else {res.json(status);}
        })
};


