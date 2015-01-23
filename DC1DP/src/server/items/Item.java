package server.items;

import server.player.Player;

public interface Item {
	public String getItemName();
	
	public String getItemDescription();
	
	public void useItem(Player p);
}
