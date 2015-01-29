function Take(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    player.notify('Perform Take');
  }

}

module.exports = Take;