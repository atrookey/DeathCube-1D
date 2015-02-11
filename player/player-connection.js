function PlayerConnection (player, socket, session) {

  this.notify = function(message){
    socket.emit(session.getEventName(), message);
  }

  this.getSocketID = function() {
    return socket.id;
  }

  socket.on(session.getEventName(), function(val) {
    session.getParser().parseInput(player, val);
  });

  socket.on('disconnect', function () {
    session.removePlayer(player);
    player.room.removePlayer(player);
    session.notifyPlayers('player disconnected');
    console.log(session.numberOfPlayers());
  });
}

module.exports = PlayerConnection;