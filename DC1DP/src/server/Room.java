package server;

import java.util.ArrayList;

import server.room.roomlisteners.OnEnterListener;
import server.room.roomlisteners.OnExitListener;

public class Room {

	public enum Direction {
		north, south, east, west, up, down;
	}

	private Room north, south, east, west, up, down;

	private ArrayList<Player> _players;
	
	private ArrayList<OnEnterListener> _onEntListeners;
	private ArrayList<OnExitListener> _onExitListeners;
	
	private String _description;

	public Room(String desc) {
		_description = desc;
		
		_players = new ArrayList<>();
		_onEntListeners = new ArrayList<>();
		_onExitListeners = new ArrayList<>();
	}

	public void setRoom(Direction d, Room r) {
		switch (d) {
		case down:
			down = r;
			break;
		case east:
			east = r;
			break;
		case north:
			north = r;
			break;
		case south:
			south = r;
			break;
		case up:
			up = r;
			break;
		case west:
			west = r;
			break;
		default:
			break;
		}
	}
	
	public Room getRoom(Direction d){
		switch (d) {
		case down:
			return down;
		case east:
			return east;
		case north:
			return north;
		case south:
			return south;
		case up:
			return up;
		case west:
			return west;
		default:
			return null;
		}
	}
	
	public String getDescription(){
		return _description;
	}
	
	public void enterRoom(Player p, Direction d){
		for(Player otherplayer : _players){
			otherplayer.alert(p.getName() + " has entered the room from the " + d + "!");
		}
		
		_players.add(p);
		
		for(OnEnterListener l : _onEntListeners){
			l.enteredRoom(p, this);
		}
		
		p.setCurrentRoom(this);
		p.appendToLog("You enter a " + _description + " room.\n");
	}
	
	public void exitRoom(Player p, Direction d){
		
		if(_players.contains(p)){
			_players.remove(p);
			
			for(OnExitListener l : _onExitListeners){
				l.exitRoom(p, this);
			}
			
			for(Player otherplayer : _players){
				otherplayer.alert(p.getName() + " has exited the room through the " + d + " door.");
			}
			
			System.out.println(p.getName() + " has exited!");
			
		}else{
			System.err.println("Invalid!");
		}
	}
	
	public void disconnectAllRooms(){
		if(north != null){
			north.south = null;
		}
		
		if(south != null){
			south.north = null;
		}
		
		if(east != null){
			east.west = null;
		}
		
		if(west != null){
			west.east = null;
		}
		
		if(up != null){
			up.down = null;
		}
		
		if(down != null){
			down.up = null;
		}
	}
	
	public void placePlayer(Player p){
		_players.add(p);
		p.setCurrentRoom(this);
		p.alert("You wake up in a " + _description + " room.");
	}
	
	public ArrayList<Player> getPlayersInRoom(){
		return _players;
	}
}
