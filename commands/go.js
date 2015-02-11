function Go(session) {

  var directions = ['north','south','east','west','up','down'];

  this.getHelp = function() {
    return 'type GO [DIRECTION] to go to that room\n'
      + 'available directions: ' + directions.toString();
  }

  function opposite(index) {
    return (index % 2 == 0 ? ++index : --index);
  }

  this.performCommand = function(player, args) {
    if (args.length < 2) {
      player.notify('you must include a direction to move in!');
    } else {
      args[1] = args[1].toLowerCase();
      var room = null;
      var direction = null;
      for(var i = 0; i<directions.length; i++) {
        if(args[1] == directions[i]){
          room = player.room[directions[i]];
          direction = i;
          break;
        }
      }
      if(direction == null) {
        player.notify('invalid direction!');
      }
      else if(room == null) {
        player.notify('there is no room there!');
      } else {
        player.room.exitPlayer(player, directions[direction]);
        room.enterPlayer(player, directions[opposite(direction)]);
      }
    }
  }

}

module.exports = Go;