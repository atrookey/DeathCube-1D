function Username(session) {

  this.getHelp = function() {
    return 'type USERNAME to view your current username\n'
        + 'type USERNAME SET [USERNAME] to change your username at any time\n'
        + '* username is set to null by default';
  }

  this.performCommand = function(player, args) {
    if(args.length == 1) {
      player.notify(player.name);
    } 
    else if(args.length == 2) {
      player.notify(this.getHelp());
    } 
    else {
      args[1] = args[1].toUpperCase();
      switch (args[1]) {
        case "SET":
          args.splice(0,2);
          player.name = args.join(' ');
          player.notify('username changed to ' + player.name);
          break;
        default:
          player.notify(this.getHelp());
          return;
      }
    }
  }
}

module.exports = Username;