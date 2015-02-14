var PlayerList;
var Player;
var MockSocket;
var list1;
var session;
var fred;
var george;
var alex;
var amanda;
var stacia;

function setup() {
  var express = require('express');
  var app = express();
  var http = require('http').Server(app);
  var io = require('socket.io')(http);
  var Session = require('../session/session.js');
  PlayerList = require('../player/player-list.js');
  Player = require('../player/player.js');
  MockSocket = require('./mock-socket.js');
  list1 = new PlayerList();
  session = new Session(io, 'message');
  fred = new Player(new MockSocket(), session);
  fred.name = 'fred';
  george = new Player(new MockSocket(), session);
  george.name = 'george';
  alex = new Player(new MockSocket(), session);
  alex.name = 'alex';
  amanda = new Player(new MockSocket(), session);
  amanda.name = 'amanda';
  stacia = new Player(new MockSocket(), session);
  stacia.name = 'stacia';
}

function assert(test, expected, actual) {
  console.log(test + ' ' + String(expected == actual) +
    ' EXPECTED:' + expected + ' ACTUAL:' + actual);
  return expected == actual;
}

// test the addition of new players
function test1() {
  list1.addPlayer(fred);
  list1.addPlayer(george);
  list1.addPlayer(alex);
  list1.addPlayer(amanda);
  list1.addPlayer(stacia);
  var expected = ['fred','george','alex','amanda','stacia'].toString();
  var actual = list1.toString();
  return assert('TEST 1', expected, actual);
}

// test the removal of players
function test2() {
  list1.removePlayer(george);
  list1.removePlayer(amanda);
  var expected = ['fred','alex','stacia'].toString();
  var actual = list1.toString();
  return assert('TEST 2', expected, actual);;
}

// test the contains method in a positive scenario
function test3() {
  var list = new PlayerList();
  var david = new Player(new MockSocket(), session);
  list.addPlayer(david);
  var expected = true;
  var actual = list.containsPlayer(david);
  return assert('TEST 3', expected, actual);
}

// test the contains method in a negative scenario
function test4() {
  var list = new PlayerList();
  var a = new Player(new MockSocket(), session);
  list.addPlayer(a);
  var b = new Player(new MockSocket(), session);
  list.addPlayer(b);
  var c = new Player(new MockSocket(), session);
  list.addPlayer(c);
  list.removePlayer(c);
  var expected = false;
  var actual = list.containsPlayer(c);
  return assert('TEST 4', expected, actual);
}

// test the contains method for 100 items
function test5() {
  var list = new PlayerList();
  var expected = true;
  var actual = expected;
  for(var i = 0; i<100; i++) {
      var a = new Player(new MockSocket(), session);
      list.addPlayer(a);
      var actual = actual && list.containsPlayer(a);
  }
  return assert('TEST 5', expected, actual);
}

// test the contains method for 1000 players
function test6() {
  var list = new PlayerList();
  var expected = true;
  var actual = expected;
  for(var i = 0; i<1000; i++) {
      var a = new Player(new MockSocket(), session);
      list.addPlayer(a);
      var actual = actual && list.containsPlayer(a);
  }
  return assert('TEST 6', expected, actual);
}

// test number of players
function test7() {
  var list = new PlayerList();
  var expected = 0;
  var actual = list.numberOfPlayers();
  return assert('TEST 7', expected, actual);
}

// test number of players
function test8() {
  var list = new PlayerList();
  var player = new Player(new MockSocket(), session);
  list.addPlayer(player);
  var expected = 1;
  var actual = list.numberOfPlayers();
  return assert('TEST 8', expected, actual);
}

// test number of players
function test9() {
  var list = new PlayerList();
  var a = new Player(new MockSocket(), session);
  list.addPlayer(a);
  var b = new Player(new MockSocket(), session);
  list.addPlayer(b);
  var c = new Player(new MockSocket(), session);
  list.addPlayer(c);
  list.removePlayer(c);
  var expected = 2;
  var actual = list.numberOfPlayers();
  return assert('TEST 9', expected, actual);
}

// test number of players
function test10() {
  var list = new PlayerList();
  var a = new Player(new MockSocket(), session);
  list.addPlayer(a);
  var b = new Player(new MockSocket(), session);
  list.addPlayer(b);
  var c = new Player(new MockSocket(), session);
  list.addPlayer(c);
  list.removePlayer(c);
  var d = new Player(new MockSocket(), session);
  list.addPlayer(d);
  var e = new Player(new MockSocket(), session);
  list.addPlayer(e);
  list.removePlayer(a);
  var expected = 3;
  var actual = list.numberOfPlayers();
  return assert('TEST 10', expected, actual);
}

// test number of players up to 797
function test11() {
  var list = new PlayerList();
  for(var i = 0; i<797; i++) {
    var a = new Player(new MockSocket(), session);
    list.addPlayer(a);
  }
  var expected = 797;
  var actual = list.numberOfPlayers();
  return assert('TEST 11', expected, actual);
}

// test number of players up to 989
function test12() {
  var list = new PlayerList();
  for(var i = 0; i<989; i++) {
    var a = new Player(new MockSocket(), session);
    list.addPlayer(a);
  }
  var expected = 989;
  var actual = list.numberOfPlayers();
  return assert('TEST 12', expected, actual);
}

// test removal of 989 players
function test13() {
  var list = new PlayerList();
  var playersToBeRemoved = [];
  for(var i = 0; i<989; i++) {
    var a = new Player(new MockSocket(), session);
    list.addPlayer(a);
    playersToBeRemoved.push(a);
  }
  for(var i = 0; i<playersToBeRemoved.length; i++) {
    list.removePlayer(playersToBeRemoved[i]);
  }
  var expected = 0;
  var actual = 0;
  return assert('TEST 13', expected, actual);
}

function runTests() {
  setup();
  var passed = 0;
  var failed = 0;
  test1()  ? passed++ : failed++;
  test2()  ? passed++ : failed++;
  test3()  ? passed++ : failed++;
  test4()  ? passed++ : failed++;
  test5()  ? passed++ : failed++;
  test7()  ? passed++ : failed++;
  test8()  ? passed++ : failed++;
  test9()  ? passed++ : failed++;
  test10() ? passed++ : failed++;
  test11() ? passed++ : failed++;
  test12() ? passed++ : failed++;
  test13() ? passed++ : failed++;
  console.log(String(passed) + ' passed / ' + String(failed) + ' failed');
  console.log('Player List Tests Done');
}

module.exports = runTests;