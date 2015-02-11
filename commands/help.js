function Help(session) {

  this.getHelp = function() {
    return  'available commands: ' + session.getParser().getCommands().toString() + '\n'
    + 'type HELP to get a list of commands\n'
    + 'type HELP COMMAND to get help for that command';
  }

  this.performCommand = function(player, args) {
    if(args.length == 1) {
      player.notify(this.getHelp());
    } 
    else {
      args[1] = args[1].toUpperCase();
      var command = session.getParser().getCommand(args[1]);
      if(command == null) {
        player.notify('Command not found');
      } else {
        player.notify(command.getHelp());
      }
    }
  }

}

module.exports = Help;