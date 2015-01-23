package server.commands;

import server.items.Item;
import server.player.Player;

public class LookFunction implements ICommand {

	public static String COMMAND = "LOOK";

	@Override
	public void performCommand(Player p, String[] args) {
		p.appendToLog("You are in a " + p.getCurrentRoom().getDescription()
				+ " room.");

		if (p.getCurrentRoom().getPlayersInRoom().size() > 1) {
			for (Player other : p.getCurrentRoom().getPlayersInRoom()) {
				if (other == p) {
					continue;
				}

				p.appendToLog(other.getName() + " is in the room.");
			}
		}

		if (!p.getCurrentRoom().getItems().isEmpty()) {
			p.appendToLog("There are currently " + p.getCurrentRoom().getItems().size() + " items in the room:");
			
			for (Item i : p.getCurrentRoom().getItems()) {
				p.appendToLog("  " + i.getItemName());
			}
		}
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return COMMAND;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Type LOOK to view your surroundings.";
	}

}
