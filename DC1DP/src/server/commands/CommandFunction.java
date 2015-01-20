package server.commands;

import server.player.Player;

public interface CommandFunction {

	public void performCommand(Player p, String[] args);
	
	public String getCommandName();
	
	public String getHelp();
}
