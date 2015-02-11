function Use(session) {

  this.getHelp = function() {
    return 'help not defined'
  }

  this.performCommand = function(player, args) {
    session.testForEach();
    player.notify('For Each Test Done');
  }

}

module.exports = Use;