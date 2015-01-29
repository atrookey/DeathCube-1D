var List = require('./list.js');

function ItemList() {

  var items = new List();

  // override remove method in List
  // need error handling
  // items.remove = function(element) {
    
  // }

  this.addItem = function(item) {
    items.add(item);
  }

  this.removeItem = function(item) {
    return items.remove(item);
  }

  this.contains = function(item) {
    return items.contains(item);
  }

  this.numberOfItems = function() {
    return items.size;
  }

}

module.exports = ItemList;