package server.room.roomlisteners;

import server.cube.Room;
import server.player.Player;

public interface OnEnterListener {
	public void enteredRoom(Player p, Room r);
}
