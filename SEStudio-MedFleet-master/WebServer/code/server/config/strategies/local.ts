import passport = require('passport');
import pl = require('passport-local');
import mongoose = require('mongoose');

var User = require('mongoose').model('User');
var LocalStrategy = pl.Strategy;

export function PassportLocal() {
    passport.use(new LocalStrategy(function (username, password, done) {
        User.findOne({
            username: username
        }, function (err, user) {
            if (err) {
                return done(err);
            }

            if (!user) {
                return done(null, false, {
                    message: 'Unknown user'
                });
            }
            if (!user.authenticate(password)) {
                return done(null, false, {
                    message: 'Invalid password'
                });
            }

            return done(null, user);
        });
    }));
};
