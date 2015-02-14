function PlayerList() {

  var players = [];

  this.addPlayer = function(player) {
    players.push(player);
  }

  this.removePlayer = function(element) {
    for(var i = 0; i<players.length; i++) {
      // Okay, I know this comparison is not great
      if(players[i].getPlayerConnection().getSocketID() == element.getPlayerConnection().getSocketID()) {
        this.array.splice(i, 1);
        return element;
      }
    }
  }

  this.containsPlayer = function(element) {
    for(var i = 0; i<players.length; i++) {
      // Okay, I know this comparison is not great
      if(players[i].getPlayerConnection().getSocketID() == element.getPlayerConnection().getSocketID()) {
        return true;
      }
    }
    return false;
  }

  this.notifyPlayers = function(message) {
    for(var i = 0; i<players.length; i++) {
      players[i].notify(message);
    }
  }

  this.numberOfPlayers = function() {
    return players.length;
  }

}

module.exports = PlayerList;