function MockSocket () {
  this.id = MockSocket.nextID++;

  this.emit = function(event, message) {
    console.log('MockSocket ' + id + ' emmitted ' + message);
    return;
  }

  this.on = function(event, callback) {
    return;
  }
}

MockSocket.nextID = 0;

module.exports = MockSocket;