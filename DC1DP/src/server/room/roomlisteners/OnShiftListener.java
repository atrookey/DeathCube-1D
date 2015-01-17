package server.room.roomlisteners;

import server.Player;
import server.Room;

public interface OnShiftListener {
	public void onShift(Player p, Room r);
}
