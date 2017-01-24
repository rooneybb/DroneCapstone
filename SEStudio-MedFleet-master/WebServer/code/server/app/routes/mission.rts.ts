//////////////// express / router
import express = require('express');
var router = express.Router();

//////////////// model / controller 
require('../models/mission.model.db');
import * as mission_controler from '../controllers/mission.controller';

//////////////// all rts start at /drones
router.route('/')
    .get(mission_controler.list)
    .post(mission_controler.create)
    .delete(mission_controler.delete_all);
   
router.route('/:missionid')
    .get(mission_controler.getOne)
    .post(mission_controler.update)
    .delete(mission_controler.delete_one);
    
router.route('/drone/:droneid')
    .get(mission_controler.getOnebyDroneId)
    .post(mission_controler.updatebyDroneId)
    
router.route('updateflightpaths/:missionid')
    .post(mission_controler.update_flt_paths);
    
export = router;
