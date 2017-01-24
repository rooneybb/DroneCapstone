//////////////// express / router
import express = require('express');
var router = express.Router();

//////////////// model / controller 
require('../models/config.model.db');
import * as config_controler from '../controllers/base_station.controller';

//////////////// all rts start at /configs
router.route('/')
    .get(config_controler.list)
    .post(config_controler.create)
    .delete(config_controler.delete_all);
    
router.route('/:bsid')
    .get(config_controler.getOne)
    .post(config_controler.update)
    .delete(config_controler.delete_one);
    
export = router;
