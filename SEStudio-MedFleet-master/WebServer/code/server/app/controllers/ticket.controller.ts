//////////////// db and express
import express  = require('express');
import mongoose = require('mongoose');


////////////######################
// todo
// 1. fix delteing a tx. not working
// 2. fix updates so not delte non updated things
// 3. create tests for invalid data



//////////////// create the ticket object 
var Ticket = mongoose.model('Ticket');

// list all tickets in json format
export function list(req: express.Request, res: express.Response) {
    console.log(req.query.status);
    Ticket.find(function(err : any, tickets: any){
        if (err) { res.send("phawker"); } 
        else     { res.json(tickets); }
    });
};

export function list_by_status(req: express.Request, res: express.Response) {
    console.log(req.query.status);
    Ticket.find(function(err : any, tickets: any){
        if (err) { res.send("phawker"); } 
        else     { res.json(tickets); }
    });
};
// create a ticket with the json object in the req body
export function create(req: express.Request, res: express.Response) {
    var ticket = new Ticket(req.body);
	ticket.save(function(err: any) {
		if (err) { res.send("phawker"); } 
        else {res.json(ticket);}
        
	});
};

// updates the ticket with id in the url and json object in the request body
export function update(req: express.Request, res: express.Response) {
    var ticket = req.body;
    Ticket.findByIdAndUpdate(req.params.ticketid,ticket, 
        function(err, ticket) {
            if (err) { res.send("phawker"); } 
            else {res.json(ticket);}
        })
};

export function resetStatus(req: express.Request, res: express.Response) {
    var ticket = req.body;
    Ticket.update({}, {$set: {status: "received"}}, {multi: true},  
        function(err, ticket) {
            if (err) { res.send("phawker"); } 
            else {res.json(ticket);}
        })
};

// gets one ticket of the id in the req url
export function get_one(req: express.Request, res: express.Response) {
    Ticket.findById(req.params.ticketid, function(err, ticket) {
        if (err) { res.send("phawker"); } 
        else {res.json(ticket);}
    })
};

// deletes the object of the id in the url 
export function delete_one(req: express.Request, res: express.Response) {
    var ticket_id = req.params.ticketid;

    Ticket.findByIdAndRemove(ticket_id, function(err, mon) {
        if (err) { res.send("phawker"); } 
        else { res.json(mon);}
    })
};

// deletes all the ticket objects
export function delete_all(req: express.Request, res: express.Response) {
    Ticket.remove(
        function(err, ticket) {
            if (err) { res.send("phawker"); } 
            else {res.json(ticket);}
        })
};

// creates tickets via info in the url
export function create_via_url (req: express.Request, res: express.Response) {
    var ticket = new Ticket( {  
        latitude: req.params.lat + "." + req.params.lat_dec,
        longitude: req.params.long + "." + req.params.long_dec,
        urgency: req.params.urgency
    });
    ticket.save(function(err: any) {
		if (err) { res.send("phawker"); } 
        else {res.json(res.statusMessage);}
	});
};

// get list of objects with status send in url ie /status/completed -> returns all completed txs
export function get_by_status (req: express.Request, res: express.Response) {
    console.log("status is " + req.params.status);
    var ticket = Ticket.find ( {'status': req.params.status },
        function(err, ticket) {
            if (err)    { res.send("phawker"); } 
             else {res.json(ticket);}
        }
    );
};