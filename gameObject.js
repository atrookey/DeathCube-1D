// Session Prototype
exports.Session = function () {
  var _players = [];

  this.addPlayer = function(player) {
    _players.push(player);
  }

  // umm, this isn't quite right either :/
  this.removePlayer = function(socket) {
    console.log('disconnect!');
    for(var i = 0; i<_players.length; i++) {
      // Okay, I know this comparison is not great, but gonna
      // go with it for now
      if(_players[i].getSocket().id == socket.id) {
        _players.splice(i, 1);
      }
    }
  }

  this.numberOfPlayers = function() {
    return _players.length;
  }

}

// Player Prototype
exports.Player =  function (socket, game) {
  var _game = game;
  var _socket = socket;
  var _name = null;
  var _currentRoom = null;

  _socket.on('message', function() {
    console.log(_game.numberOfPlayers());
  });

  _socket.on('disconnect', function () {
    _game.removePlayer(this);
  });

  this.setCurrentRoom = function(room) {
    _currentRoom = room;
  };

  this.getCurrentRoom = function() {
    return _currentRoom;
  };

  this.setName = function(name) {
    _name = name;
  };

  this.getName = function() {
    return _name;
  }

  this.getSocket = function() {
    return _socket;
  }
}

// Cube Prototype
exports.