package server.commands;

import server.player.Player;

public class InspectFunction implements ICommand{

	public static final String COMMAND = "INSPECT";
	
	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 2){
			p.appendToLog("Invalid usage! Type HELP INSPECT for more info!");
		}else{
			if(p.getInventory().getItem(args[1]) != null){
				p.appendToLog(p.getInventory().getItem(args[1]).getItemDescription());
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
		return "Usage: INSPECT <ITEM>.";
	}

}
