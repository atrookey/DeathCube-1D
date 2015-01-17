package server.room.roomlisteners;

import server.Player;
import server.Room;

public interface OnPeekListener {
	public void onPeek(Player p, Room r);
}
