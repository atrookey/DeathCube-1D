package server;

import java.io.IOException;

import shared.ServerPacket;

public class Player {
	//The players name
	private String _name;
	
	//The current room he/she is in
	private Room _currentRoom;

	//This keeps track of all of the output the player receives.
	//At the end of the "Action" it is then sent back to the player.
	//This is for nonimmeadiate notifications.
	private StringBuilder _log;
	
	//The connection that is serving this player. It allows for server communication.
	private PlayerConnection _pc;

	/** 
	 * @param name the player's name
	 * @param pc the connection that is hosting this player
	 */
	public Player(String name, PlayerConnection pc) {
		_name = name;
		_log = new StringBuilder();
		_pc = pc;
	}

	public void setCurrentRoom(Room r) {
		_currentRoom = r;

	}

	public Room getCurrentRoom(){
		return _currentRoom;
	}
	
	/**
	 * Appends a message to the log. It appends it with a \n.
	 * @param s the message to be appended.
	 */
	public synchronized void appendToLog(String s) {
		_log.append(s + "\n");
	}

	public String getName(){
		return _name;
	}
	
	/**
	 * Returns the log. This method creates a new log when used!
	 * In order to "peek" at the log use {@link #retrieveIncompleteLog() retrieveIncompleteLog} method.
	 * @return the player log.
	 */
	public String retrieveLog() {
		String s = _log.toString();
		_log = new StringBuilder();
		return s;
	}
	
	/**
	 * Returns the log without erasing and making a new one.
	 * @return the player log.
	 */
	public String retrieveIncompleteLog(){
		return _log.toString();
	}
	
	/**
	 * Sends a message to the player in real time. Note: this calls a
	 * synchronized method and may cause the thread to sleep!
	 * 
	 * @param s the message to be sent to the player's console.
	 */
	public void alert(String s){
		try {
			_pc.notifyPlayer(new ServerPacket(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Could not notify the player!");
		}
	}
}
