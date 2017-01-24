//////////////// express / router
import express = require('express');
var router = express.Router();

//////////////// model / controller 
require('../models/config.model.db');
import * as config_controler from '../controllers/config.controller';

//////////////// all rts start at /configs
router.route('/')
    .get(config_controler.list)
    .post(config_controler.create)
    .delete(config_controler.delete_all);
    
router.route('/:configid')
    .get(config_controler.getOne)
    .post(config_controler.update)
    .delete(config_controler.delete_one);

router.route('/name/:configname')
    .get(config_controler.getByName)
    .post(config_controler.updateByName)
    .delete(config_controler.deleteByName);
    
export = router;
