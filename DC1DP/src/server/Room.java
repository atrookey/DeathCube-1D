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
	
	public void enterRoom(Player p){
		for(Player otherplayer : _players){
			Server.getServer().notifyPlayer(otherplayer, p.getName() + " has entered the room!");
		}
		
		_players.add(p);
		
		for(OnEnterListener l : _onEntListeners){
			l.enteredRoom(p, this);
		}
		
		p.setCurrentRoom(this);
		p.appendToLog("You enter a " + _description + " room.\n");
	}
	
	public void exitRoom(Player p){
		
		if(_players.contains(p)){
			_players.remove(p);
			
			for(OnExitListener l : _onExitListeners){
				l.exitRoom(p, this);
			}
			
			for(Player otherplayer : _players){
				Server.getServer().notifyPlayer(otherplayer, p.getName() + " has exited the room!");
			}
			
			System.out.println(p.getName() + " has exited!");
			
		}else{
			System.err.println("Invalid!");
		}
	}
	
	public ArrayList<Player> getPlayersInRoom(){
		return _players;
	}
}
