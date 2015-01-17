package server.room.roomlisteners;

import server.Player;
import server.Room;

public interface OnExitListener {
	public void exitRoom(Player p, Room r);
}
