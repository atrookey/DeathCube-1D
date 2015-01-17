package server.commands;

import server.Player;

public interface CommandFunction {

	public void performCommand(Player p, String[] args);
}