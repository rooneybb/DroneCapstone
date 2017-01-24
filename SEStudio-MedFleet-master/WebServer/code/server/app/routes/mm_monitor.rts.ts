//////////////// express / router
import express = require('express');
var router = express.Router();

//////////////// model / controller 
import * as mm_controler from '../controllers/mm_monitor.controllers';

//////////////// routes / all start with /tickets
router.route('/')
    .get(mm_controler.renderIndex);

/// redirect ng2 rts if it gets to the express server the we redirect back to index
router.route('/dashboard')
    .get(mm_controler.renderIndex);

router.route('/droneslist')
    .get(mm_controler.renderIndex);

router.route('/ticketlist')
    .get(mm_controler.renderIndex);

router.route('/map')
    .get(mm_controler.renderIndex);
export = router;