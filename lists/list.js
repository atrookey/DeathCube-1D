function List() {
  this.array = [];
}

List.prototype.add = function(element) {
  this.array.push(element);
}

List.prototype.remove = function(element) {
  for(var i = 0; i<this.array.length; i++) {
    if(this.array[i] == element) {
      this.array.splice(i, 1);
      return item;
    }
  }
}

List.prototype.contains = function(element) {
  for(var i = 0; i<this.array.length; i++) {
    if(this.array[i] == element) {
      return true;
    }
  }
  return false;
}

List.prototype.size = function() {
  return this.array.length;
}

module.exports = List;