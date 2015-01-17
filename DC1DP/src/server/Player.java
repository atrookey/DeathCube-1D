package server;

import java.io.IOException;

import shared.ServerPacket;

public class Player {
	private String _name;
	private Room _currentRoom;

	private StringBuilder _log;
	
	private PlayerConnection _pc;

	public Player(String n, PlayerConnection pc) {
		_name = n;
		_log = new StringBuilder();
		_pc = pc;
	}

	public void setCurrentRoom(Room r) {
		_currentRoom = r;

	}

	public Room getCurrentRoom(){
		return _currentRoom;
	}
	
	public void appendToLog(String s) {
		_log.append(s + "\n");
	}

	public String getName(){
		return _name;
	}
	
	public String retrieveLog() {
		String s = _log.toString();
		_log = new StringBuilder();
		return s;
	}
	
	public void alert(String s){
		try {
			_pc.notifyPlayer(new ServerPacket(s, null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
