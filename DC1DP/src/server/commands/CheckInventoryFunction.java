package server.commands;

import server.player.Inventory;
import server.player.Player;

public class CheckInventoryFunction implements ICommand{

	public static final String COMMAND = "CHECK";
	
	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		String s = p.getInventory().getListOfItems();
		
		if(s.equals("")){
			p.appendToLog("Your inventory is empty!");
		}else{
			p.appendToLog("The current items in your inventory:");
			p.appendToLog(s);
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
		return "Usage: Type CHECK to check your inventory.";
	}

}
