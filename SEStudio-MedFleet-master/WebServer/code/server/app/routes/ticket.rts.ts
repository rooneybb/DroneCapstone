//////////////// express / router
import express = require('express');
var router     = express.Router();


//////////////// model / controller 
require('../models/ticket.model.db');
import * as ticket_controler from '../controllers/ticket.controller';

//////////////// routes / all start with /tickets
router.route('/')
    .get(ticket_controler.list)
    .post(ticket_controler.create)
    .delete(ticket_controler.delete_all);

router.route('/:ticketid')
    .get(ticket_controler.get_one)
    .post(ticket_controler.update)
    .delete(ticket_controler.delete_one);
    
router.route('/status/:status')
    .get(ticket_controler.get_by_status);

router.route('/:lat?/:lat_dec?/:long?/:long_dec?/:urgency?')
    .post(ticket_controler.create_via_url)
    .get(ticket_controler.create_via_url);
    
// helper rt to creset status to origanl state 
router.route('/resetstatus')
    .get(ticket_controler.resetStatus)
    
export = router;