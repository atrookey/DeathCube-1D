package server.commands;

import server.Player;
import server.Room;
import server.Server;

public class MapFunction implements CommandFunction {

	public static String COMMAND = "MAP";
	
	private Server _s;
	
	public MapFunction(Server s) {
		_s = s;
	}

	@Override
	public void performCommand(Player p, String[] args) {
		final Room[][][] cube = _s.getCube().getCube();

		StringBuilder sb = new StringBuilder();

		for (int z = 0; z < cube[0][0].length; z++) {
			sb.append("Level " + z + ":\n");
			for (int y = cube[0].length-1; y >= 0; y--) {
				for (int x = 0; x < cube.length; x++) {
					if (cube[x][y][z] == null) {
						sb.append("X");
					} else {

						sb.append(cube[x][y][z].getDescription().charAt(0));

						if (cube[x][y][z].getPlayersInRoom().contains(p)) {
							sb.append("*");
						}
					}
					
					sb.append("|");
				}

				sb.append("\n");
			}
		}
		
		p.appendToLog(sb.toString());
	}

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return COMMAND;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "Type MAP. This is for (dev?) only.";
	}

}
