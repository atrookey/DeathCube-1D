package server.commands;

import server.cube.Room;
import server.items.Item;
import server.player.Player;

public class TakeFunction implements ICommand{

	public static final String COMMAND = "TAKE";
	
	
	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 2){
			p.appendToLog("Invalid usage! Type HELP TAKE for help.");
		}else{
			Room r = p.getCurrentRoom();
			
			boolean found = false;
			
			for(int i = 0; i < r.getItems().size(); i++){
				
				if(r.getItems().get(i).getItemName().equals(args[1])){
					p.getInventory().putItem(r.getItems().get(i));
					r.getItems().remove(i);
					found = true;
				}
			}
			
			if(found){
				p.appendToLog("Item added to your inventory!");
			}else{
				p.appendToLog("That item is not in this room!");
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
		return "Usage: TAKE <ITEM>";
	}

}
