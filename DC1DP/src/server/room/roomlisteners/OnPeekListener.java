package server.room.roomlisteners;

import server.cube.Room;
import server.player.Player;

public interface OnPeekListener {
	public void onPeek(Player p, Room r);
}
