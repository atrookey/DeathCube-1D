function MockSocket (id_num) {
  this.id = id_num;

  this.emit = function(event, message) {
    console.log('MockSocket ' + id + ' emmitted ' + message);
    return;
  }

  this.on = function(event, callback) {
    return;
  }
}

module.exports = MockSocket;