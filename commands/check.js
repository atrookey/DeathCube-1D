function Check(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    player.notify('Perform Check');
  }

}

module.exports = Check;