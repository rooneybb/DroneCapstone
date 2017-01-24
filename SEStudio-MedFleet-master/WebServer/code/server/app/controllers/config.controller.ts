//////////////// db / express
import express  = require("express");
import mongoose = require('mongoose');

//////////////// import config model
var Config = mongoose.model('Config');

//////////////// functions

// list all configs in json format
export function list(req: express.Request, res: express.Response) {
    Config.find(function(err : any, conifgs: any){
        if (err) { console.log(err); } 
        else     { res.json(conifgs); }
    });
    
};

// create new configs from json object in body of request
export function create(req: express.Request, res: express.Response) {
    var config = new Config(req.body);
    
	config.save(function(err: any) {
		if (err) { console.log(err); } 
        else {res.json(config);}
	});
};

// update a config via json object in body and id in the url 
export function update(req: express.Request, res: express.Response) {
    var config = req.body;
    Config.findByIdAndUpdate(req.params.configid,config, 
        function(err, config) {
            if (err) { console.log(err); } 
            else {res.json(config);}
        })
};

// returns one configs via the id in the url
export function getOne(req: express.Request, res: express.Response) {
    Config.findById(req.params.configid, 
        function(err, config) {
            if (err) { console.log(err); } 
            else {res.json(config);}
    })
};

// deletes the configs with the id in the url
/// todo create with call back of status 
//query.remove({ name: 'Anne Murray' }).remove(callback)
export function delete_one(req: express.Request, res: express.Response) {
    var config = req.body;
    Config.findByIdAndRemove(req.params.configid,config, 
        function(err, status) {
            if (err) { console.log(err); } 
            else {res.json(status);}
        })
};

// deletes all the configs objects
export function delete_all(req: express.Request, res: express.Response) {
    Config.remove(
        function(err, status) {
            if (err) { res.send("phawker"); } 
            else {res.json(status);}
        })
};

// returns one configs via the id in the url
export function getByName(req: express.Request, res: express.Response) {
    Config.findOne({'name' : req.params.configname},
        function(err, config) {
            if (err) { console.log(err); } 
            else {res.json(config);}
    })
};


export function updateByName(req: express.Request, res: express.Response) {
    var config = req.body;
    Config.findByIdAndUpdate(req.params.configname,config, 
        function(err, config) {
            if (err) { console.log(err); } 
            else {res.json(config);}
        })
};

export function deleteByName(req: express.Request, res: express.Response) {
    var config = req.body;
    Config.findByIdAndRemove(req.params.configname,config, 
        function(err, status) {
            if (err) { console.log(err); } 
            else {res.json(status);}
        })
};

