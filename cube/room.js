var PlayerList = require('../lists/player-list.js');

function Room(description) {
  var players = new PlayerList();
  this.north = null;
  this.south = null;
  this.east = null;
  this.west = null;
  this.up = null;
  this.down = null;

  this.addPlayer = function(player) {
    players.addPlayer(player);
    player.room = this;
  }

  this.removePlayer = function(player) {
    return players.removePlayer(player);
  }

  this.enterPlayer = function(player, direction) {
    this.notifyPlayers(player.name + ' just entered from ' + direction);
    this.addPlayer(player);
    player.notify('you entered a ' + description + ' room');
  }

  this.exitPlayer = function(player, direction) {
    players.removePlayer(player);
    this.notifyPlayers(player.name + ' just exited ' + direction);
  }

  this.numberOfPlayers = function() {
    return players.numberOfPlayers();
  }

  this.containsPlayer = function(player) {
    return players.containsPlayer(player);
  }

  this.notifyPlayers = function(message) {
    players.notifyPlayers(message);
  }

  this.getDescription = function() {
    return description;
  }

}

module.exports = Room;