package server.commands;

import server.Player;

public class LookFunction implements CommandFunction{

	public static String COMMAND = "LOOK";
	
	@Override
	public void performCommand(Player p, String[] args) {
		p.appendToLog("You are in a " + p.getCurrentRoom().getDescription() + " room.");
		
		if(p.getCurrentRoom().getPlayersInRoom().size() > 1){
			for(Player other : p.getCurrentRoom().getPlayersInRoom()){
				if(other == p){
					continue;
				}
				
				p.appendToLog(other.getName() + " is in the room.");
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
