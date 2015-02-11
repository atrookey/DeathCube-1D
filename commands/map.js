function Map(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    player.notify(session.getCube().map(player));
  }

}

module.exports = Map;