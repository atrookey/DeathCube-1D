var express = require('express')
var app = express();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var gameObject = require('./gameObject.js');

app.use('/static', express.static(__dirname + '/public'));
app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

var _sessions = [new gameObject.Session()];
var _currentSession = _sessions[0];

// Basically PlayerConnection.java
io.on('connection', function(socket){
  _currentSession.addPlayer(new gameObject.Player(socket, _currentSession));
  // _session.getCube().placePlayer(p);
});

http.listen(3000, function(){
  console.log('listening on *:3000');
});