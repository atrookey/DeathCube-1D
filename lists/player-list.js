var List = require('./list.js');

function PlayerList() {

  var players = new List();

  // override remove method in List
  // removal based on socket id comparison
  // need error handling if element not a socket
  players.remove = function(element) {
      for(var i = 0; i<this.array.length; i++) {
      // Okay, I know this comparison is not great
      if(this.array[i].getPlayerConnection().getSocketID() == element.getPlayerConnection().getSocketID()) {
        this.array.splice(i, 1);
        return element;
      }
    }
  }

  players.contains = function(element) {
    for(var i = 0; i<this.array.length; i++) {
      // Okay, I know this comparison is not great
      if(this.array[i].getPlayerConnection().getSocketID() == element.getPlayerConnection().getSocketID()) {
        return true;
      }
    }
    return false;
  }

  players.notifyPlayers = function(message) {
    for(var i = 0; i<players.size(); i++) {
      this.array[i].notify(message);
    }
  }

  this.addPlayer = function(player) {
    players.add(player);
  }

  this.removePlayer = function(player) {
    return players.remove(player);
  }

  this.containsPlayer = function(player) {
    return players.contains(player);
  }

  this.numberOfPlayers = function() {
    return players.size();
  }

  this.notifyPlayers = function(message) {
    players.notifyPlayers(message);
  }

}

module.exports = PlayerList;