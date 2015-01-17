package server.commands;

import server.Player;
import server.Room;
import server.Room.Direction;

public class GoFunction implements CommandFunction{

	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		Direction d = null;
		
		switch(args[1]){
		case "NORTH":
			d = Direction.north;
			break;
		case "SOUTH":
			d = Direction.south;
			break;
		case "EAST":
			d = Direction.east;
			break;
		case "WEST":
			d = Direction.west;
			break;
		case "UP":
			d = Direction.up;
			break;
		case "DOWN":
			d = Direction.down;
			break;
		default:
			p.appendToLog("Invalid direction!");
			return;
		}
		
		Room r = p.getCurrentRoom().getRoom(d);
		
		if(r == null){
			p.appendToLog("There is no room there!");
		}else{
			p.getCurrentRoom().exitRoom(p);
			r.enterRoom(p);
		}
	}

}