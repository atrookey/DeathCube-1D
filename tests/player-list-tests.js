var PlayerList = require('../lists/player-list.js');
var Player = require('../player/player.js');
var list1;
var passed;
var failed;

function setup() {
  list1 = new PlayerList();
}

function assert(test, expected, actual) {
  console.log(test + ' ' + String(expected == actual) + '\n' +
    '  expected: ' + expected + '\n' + '  actual: ' + actual);
  return expected == actual;
}

function test1() {
  // var fred = new Player();
  // player1.name = 'fred';
  // var george = new Player();
  // player1.name = 'george';
  // var alex = new Player();
  // player1.name = 'alex';
  // var amanda = new Player();
  // player1.name = 'amanda';
  // var stacia = new Player();
  // player1.name = 'stacia';
  // list1.add(fred);
  // list1.add(george);
  // list1.add(alex);
  // list1.add(amanda);
  // list1.add(stacia);
  var expected = [
    'fred',
    'george',
    'alex',
    'amanda',
    'stacia'
  ].toString();
  var actual = list1.toString();
  return assert('test 1', expected, actual);
}

function test2() {
  var expected = true;
  var actual = false;
  return assert('test 2', expected, actual);;
}

function test3() {
  var expected = true;
  var actual = false;
  return assert('test 3', expected, actual);
}

function test4() {
    var expected = true;
  var actual = false;
  return assert('test 4', expected, actual);
}

function test5() {
  var expected = true;
  var actual = false;
  return assert('test 5', expected, actual);
}

function test7() {
  var expected = true;
  var actual = false;
  return assert('test 6', expected, actual);
}

function test8() {
  var expected = true;
  var actual = false;
  return assert('test 7', expected, actual);
}

function test9() {
  var expected = true;
  var actual = false;
  return assert('test 8', expected, actual);
}

function test10() {
  var expected = true;
  var actual = false;
  return assert('test 9', expected, actual);
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
  console.log(String(passed) + ' passed / ' + String(failed) + ' failed');
  console.log('Player List Tests Done');
}

module.exports = runTests;