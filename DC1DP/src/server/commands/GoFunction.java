package server.commands;

import server.Player;
import server.Room;
import server.Room.Direction;

public class GoFunction implements CommandFunction {

	public static String COMMAND = "GO";

	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		Direction d = null;

		switch (args[1]) {
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

		if (r == null) {
			p.appendToLog("There is no room there!");
		} else {
			p.getCurrentRoom().exitRoom(p,d);
			
			
			r.enterRoom(p,getOppositeDirection(d));
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
		return "type GO [DIRECTION] to go to that room. "
				+ "Available directions: north,south,west,east,up,down.";
	}
	
	public static Direction getOppositeDirection(Direction d){
		switch(d){
		case down:
			return Direction.up;
		case east:
			return Direction.west;
		case north:
			return Direction.south;
		case south:
			return Direction.north;
		case up:
			return Direction.down;
		case west:
			return Direction.east;
		default:
			return d;
		}
	}

}