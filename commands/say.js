function Say(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    player.notify('Perform Say');
  }

}

module.exports = Say;