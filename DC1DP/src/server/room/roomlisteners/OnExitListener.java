package server.room.roomlisteners;

import server.cube.Room;
import server.player.Player;

public interface OnExitListener {
	public void exitRoom(Player p, Room r);
}
