package server.commands;

import server.player.Player;

public class SayFunction implements ICommand {

	public static String COMMAND = "SAY";

	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		if (p.getCurrentRoom().getPlayersInRoom().size() < 2) {
			p.appendToLog("There is no one else here.");
		} else {

			StringBuilder sb = new StringBuilder();

			sb.append(p.getName() + " says \"");

			for (int i = 1; i < args.length; i++) {
				sb.append(args[i]);

				if (i < args.length - 1) {
					sb.append(" ");
				}
			}

			sb.append("\"");

			String s = sb.toString();

			for (Player player : p.getCurrentRoom().getPlayersInRoom()) {
				if (player == p) {
					continue;
				} else {
					player.alert(s);
				}
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
		return "Type SAY [THINGS TO SAY] to talk to everyone in that room.";
	}

}
