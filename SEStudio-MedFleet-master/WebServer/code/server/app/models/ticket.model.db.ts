import mongoose = require('mongoose');

var status_enum = 'received acknowledged scheduled on_route completed'.split(' ');

var ticketSchema = new mongoose.Schema({
    latitude:   { type: Number, min: -180, max: 180 },
    longitude:  { type: Number, min: -180, max: 180 },
    altitude:   { type: Number, min: 0, default: 0},
    urgency:    { type: Number, min: 0, max: 5, default: 0},
    status:     { type: String, enum: status_enum, default: 'received'},
    received:   { type: Date, default: Date.now, expires: '24h' },
    completed:  { type: String, default: null },
    is_a:           {type: String, default: "ticket"}
});

var Ticket = mongoose.model('Ticket', ticketSchema);

export = Ticket;