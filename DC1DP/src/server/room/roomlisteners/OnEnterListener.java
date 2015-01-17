package server.room.roomlisteners;

import server.Player;
import server.Room;

public interface OnEnterListener {
	public void enteredRoom(Player p, Room r);
}
