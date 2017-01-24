//////////////// express / router
import express = require('express');
var router = express.Router();

//////////////// model / controller 
require('../models/drone.model.db');
import * as drone_controler from '../controllers/drone.controller';

//////////////// all rts start at /drones
router.route('/')
    .get(drone_controler.list)
    .post(drone_controler.create)
    .delete(drone_controler.delete_all);
    

router.route('/:droneid')
    .get(drone_controler.getOne)
    .post(drone_controler.update)
    .delete(drone_controler.delete_one);
    
export = router;
