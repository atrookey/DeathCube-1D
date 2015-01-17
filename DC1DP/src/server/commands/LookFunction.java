package server.commands;

import server.Player;
import server.Room;

public class LookFunction implements CommandFunction{

	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
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

}
