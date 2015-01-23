package server.items;

import server.cube.Room;
import server.player.Player;
import server.room.roomlisteners.OnEnterListener;
import server.room.roomlisteners.OnExitListener;

public class BeeperItem implements Item {

	public static final String ITEM_NAME = "BEEPER";

	private static final String description = "When placed in a room, it will beep when a player enters or exits the room";

	@Override
	public String getItemName() {
		// TODO Auto-generated method stub
		return ITEM_NAME;
	}

	@Override
	public String getItemDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public void useItem(Player p) {
		// remove this item
		p.getInventory().removeItem(ITEM_NAME);

		EnterListener el = new EnterListener(p);

		p.getCurrentRoom().addOnEnterListener(el);
		p.getCurrentRoom().addOnExitListener(el);
	}

	private class EnterListener implements OnEnterListener, OnExitListener {

		private Player _owner;

		public EnterListener(Player owner) {
			_owner = owner;
		}

		@Override
		public void enteredRoom(Player p, Room r) {
			// TODO Auto-generated method stub
			if (!p.equals(_owner)) {
				_owner.alert("Beep! Beep!");
			}
		}

		@Override
		public void exitRoom(Player p, Room r) {
			if (!p.equals(_owner)) {
				_owner.alert("Beep! Beep!");
			}
		}

	}
}
