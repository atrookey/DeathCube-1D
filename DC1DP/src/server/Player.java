package server;

public class Player {
	private String _name;
	private Room _currentRoom;

	private StringBuilder _log;

	public Player(String n) {
		_name = n;
		_log = new StringBuilder();
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
		
	}
}
