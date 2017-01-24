import {ConfigEnv} from './config.interface';

// Invoke 'strict' JavaScript mode
'use strict';
// Set the 'development' environment configuration object

export var DevEnv: ConfigEnv = {
    environment: 'development' ,
    db: {
        connection: 'mongodb://localhost/medfleet',
    }, 
    google: {
        clientID: 'Google Application ID',
        clientSecret: 'Google Application Secret',
        callbackURL: 'http://localhost:3000/oauth/google/callback'
    },
    express: {
        port: 3000
    },
    sessionSecret: 'asdf'
};