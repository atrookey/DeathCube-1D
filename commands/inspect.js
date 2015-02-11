function Inspect(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    player.notify('Perform Inspect');
  }

}

module.exports = Inspect;