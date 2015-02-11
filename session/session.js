var Cube = require('../cube/cube.js');
var PlayerList = require('../lists/player-list.js');
var Parser = require('../commands/parser.js');

function Session (io, eventName) {
  var DIMENSION = 3;
  var cube = new Cube(DIMENSION,DIMENSION,DIMENSION);
  var players = new PlayerList();
  var parser = new Parser(this);
  
  this.getCube = function() {
    return cube;
  }

  this.addPlayer = function(player) {
    players.addPlayer(player);
  }

  this.removePlayer = function(player) {
    return players.removePlayer(player);
  }

  this.numberOfPlayers = function() {
    return players.numberOfPlayers();
  }

  this.notifyPlayers = function(message) {
    io.emit(eventName, message);
  }

  this.getEventName = function() {
    return eventName;
  }

  this.getParser = function() {
    return parser;
  }

}

module.exports = Session;
