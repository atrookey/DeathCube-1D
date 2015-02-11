var PlayerConnection = require('./player-connection.js');

function Player (socket, session) {
  
  this.name = null;
  this.room = null;
  var playerConnection = new PlayerConnection(this, socket, session);

  this.notify = function(message){
    playerConnection.notify(message);
  }

  this.getPlayerConnection = function() {
    return playerConnection;
  }

}

module.exports = Player;