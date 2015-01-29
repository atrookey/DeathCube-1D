var Check = require('./check.js');
var Go = require('./go.js');
var Help = require('./help.js');
var Inspect = require('./inspect.js');
var Look = require('./look.js');
var Map = require('./map.js');
var Say = require('./say.js');
var Take = require('./take.js');
var Use = require('./use.js');
var Username = require('./username.js');


function Parser (session) {

  var commands = {};

  function registerCommands() {
    commands = {
      CHECK: new Check(session),
      GO: new Go(session),
      HELP: new Help(session),
      INSPECT: new Inspect(session),
      LOOK: new Look(session),
      MAP: new Map(session),
      SAY: new Say(session),
      TAKE: new Take(session),
      USE: new Use(session),
      USERNAME: new Username(session)
    };
  }

  registerCommands();

  this.parseInput = function(player, val) {
    var args = val.split(' ');
    args[0] = args[0].toUpperCase();
    if(args.length < 1 || !(args[0] in commands)) {
      player.notify('invalid command! type help for more info.');
    } else {
      commands[args[0]].performCommand(player, args);
    }
  }

  this.getCommand = function(command) {
    if(command in commands) {
      return commands[command];
    } else {
      return null;
    }
  }

  this.getCommands = function() {
    return Object.keys(commands);
  }

}

module.exports = Parser;