package server.commands;

import server.player.Player;

public interface ICommand {

	public void performCommand(Player p, String[] args);
	
	public String getCommandName();
	
	public String getHelp();
}
