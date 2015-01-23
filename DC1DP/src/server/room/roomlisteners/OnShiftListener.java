package server.room.roomlisteners;

import server.cube.Room;
import server.player.Player;

public interface OnShiftListener {
	public void onShift(Player p, Room r);
}
