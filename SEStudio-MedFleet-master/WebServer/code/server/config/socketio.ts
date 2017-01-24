// import {config} from './config';
// import cookieParser = require('cookie-parser');

// module.exports = function(server, io, mongoStore) {
//   io.use(function(socket, next) {
//     cookieParser(config.sessionSecret)(socket.request, {}, function(err) {
//       var sessionId = socket.request.signedCookies['connect.sid'];

//       mongoStore.get(sessionId, function(err, session) {
//         socket.request.session = session;

//       });
//     });
//   });

//   io.on('connection', function(socket) {
//     require('../app/controllers/chat.server.controller')(io, socket);
//   });
// };