function Say(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    if (args.length < 2) {
        player.notify('you must include a message to say!');
    } else {
        player.room.notifyPlayers(args[2]);
    }
  }
}

module.exports = Say;