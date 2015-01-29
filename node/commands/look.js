function Look(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    player.notify('Perform Look');
  }

}

module.exports = Look;