// Takes care of REPL.java

$(function() {

  var socket = io();

  $('form').submit(function(){
        socket.emit('message', $('#m').val());
        $('#messages').append($('<li>').text('$ ' + $('#m').val()));
        $('#m').val('');
        return false;
      });

  socket.on('message', function(msg){
        $('#messages').append($('<li>').text(msg));
        $(document).scrollTop($(document).height());
      });

});