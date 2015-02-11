function Say(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    if (args.length < 2) {
        player.notify('you must include a message to say!');
    } else {
        args.splice(0, 1);
        player.room.notifyPlayers(args.join(' '));
    }
  }
}

module.exports = Say;