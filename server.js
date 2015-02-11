var express = require('express');
var app = express();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var Session = require('./session/session.js');
var Player = require('./player/player.js');

var port = var port = process.env.PORT || 5000
var DEFAULT_EVENT_NAME = 'message';

// Session creation needs to be revised
var sessions = [];
var currentSession = new Session(io, DEFAULT_EVENT_NAME);
sessions.push(currentSession);

app.use('/static', express.static(__dirname + '/public'));
app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(socket){
  var player = new Player(socket, currentSession);
  currentSession.addPlayer(player);
  var room = currentSession.getCube().placePlayer(player);
  player.notify("You wake up in a " + room.getDescription() + " room.");
});

http.listen(port, function(){
  console.log('listening on *:3000');
});
