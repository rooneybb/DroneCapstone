import {ConfigEnv} from './env/config.interface'
import {DevEnv} from './env/development';
import {ProdEnv} from './env/production';
import {TestEnv} from './env/test';

var envSettings = process.env.NODE_ENV;

// Invoke 'strict' JavaScript mode
'use strict';

var env: ConfigEnv; 

switch (envSettings) {
    case 'production': 
        env = ProdEnv;
        break;
    case 'test':
        env = TestEnv;
        break;
    default: 
        env = DevEnv;
        break;
}

export var config = env;