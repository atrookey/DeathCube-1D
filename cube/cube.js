/*
 * Cube Prototype
 * A note on directions:
 * Directions will be defined as followed for a 3 dimensional array
 * 
 * North: [x][y + 1][z]
 * South: [x][y - 1][z]
 * East:  [x - 1][y][z]
 * West:  [x + 1][y][z]
 * Up:    [x][y][z + 1]
 * Down:  [x][y][z - 1]
 * 
 * This leads to the following coordinates:
 * Someone at [0][0][0] is on the bottom layer, southeast corner
 * because you can not go any further south or east.
 * 
 * Someone at [x][y][z] are at the top layer, northwest corner
 * because you can not go any further north or west.
 * 
 * It is easiest to picture if you picture a layer of squares.
 *  _______
 * |   |   | ^ N
 * |___|___| |
 * |   |   | |
 * |___|___| |
 * x------>  y S
 * E       W 
 * 
 * Z then represents which layer you're on.
 */

var Room = require('./room.js');

function Cube (length, width, height) {

  // percent chance of a blank room
  var BLANK_CHANCE = 15;
  // Stores rooms
  var cube = new Array(length);
  var blanksMade = 0;
  var roomsMade = 0;
  // specify the maximum number of empty spots to make
  // If length * width * height = 125 then no more than 25 empty spaces
  // will be created
  var numberOfBlanks = (length * width * height) / 5;
  var colors = ['blue', 'green', 'yellow', 'red'];

  for(var i = 0; i<length; i++) {
    cube[i] = new Array(width);
    for(var j = 0; j<width; j++) {
      cube[i][j] = new Array(height);
      for(var k = 0; k<height; k++) {
        var blankChance = Math.floor(Math.random() * 100);
        if(blankChance < BLANK_CHANCE && blanksMade < numberOfBlanks) {
          cube[i][j][k] = null;
          blanksMade++;
        } else {
          var r = new Room(colors[Math.floor(Math.random()*4)]);
          cube[i][j][k] = r;
          roomsMade++;
        }
      }
    }
  }
  console.log('roomsMade: '+roomsMade);
  console.log('blanksMade: '+blanksMade);

  var connectRooms = function() {
    for(var i = 0; i<cube.length; i++) {
      for(var j = 0; j<cube[i].length; j++) {
        for(var k = 0; k<cube[i][j].length; k++) {
          var r = cube[i][j][k];
          if(r == null) {
            continue;
          }
          else {
            // north ([j + 1])
            try {
              r.north = cube[i][j + 1][k];
            } catch (err) {
              r.north = null;
            }
            // south ([j-1]
            try {
              r.south = cube[i][j - 1][k];
            } catch (err) {
              r.south = null;
            }
            // east [i + 1]
            try {
              r.east = cube[i + 1][j][k];
            } catch (err) {
              r.east = null;
            }
            // west [i - 1]
            try {
              r.west = cube[i - 1][j][k];
            } catch (err) {
              r.west = null;
            }
            // up [k + 1]
            try {
              r.up = cube[i][j][k + 1];
            } catch (err) {
              r.up = null;
            }
            // down [k + 1]
            try {
              r.down = cube[i][j][k - 1];
            } catch (err) {
              r.down = null;
            }
          }
        }
      }
    }
  }

  connectRooms();

  this.shiftRooms = function() {

  }

  var attemptShift = function() {

  }

  this.placePlayer = function(player) {
    var r = null;
    while(r == null) {
      var x = Math.floor(Math.random()*length);
      var y = Math.floor(Math.random()*height);
      var z = Math.floor(Math.random()*width);
      r = cube[x][y][z];
    }
    r.addPlayer(player);
    return r;
  }

  this.map = function(player) {
    var map = '';
    for (var z = 0; z < cube[0][0].length; z++) {
      map = map + 'Level ' + z + ':\n';
      for (var y = cube[0].length-1; y >= 0; y--) {
        for (var x = 0; x < cube.length; x++) {
          if (cube[x][y][z] == null) {
            map = map + 'x';
          } 
          else {
            map = map + cube[x][y][z].getDescription().charAt(0);
            if(cube[x][y][z].containsPlayer(player)){
              map = map + '*';
            }
          }
          map = map + '|';
        }
        map = map + '\n';
      }
    }
    return map;
  }

}

module.exports = Cube;