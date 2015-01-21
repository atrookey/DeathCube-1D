package server.commands;

import server.items.Item;
import server.player.Player;

public class UseFunction implements ICommand {

	public static final String COMMAND = "USE";

	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 2) {
			p.appendToLog("Invalid usage! Type HELP USE for more help!");
		} else {

			Item i = p.getInventory().getItem(args[1]);
			
			if(i != null){
				p.appendToLog("Item used!");
				i.useItem(p);
			}else{
				p.appendToLog("You don't currently have that item in your inventory!");
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
		return "Usage: USE <ITEM>";
	}

}
