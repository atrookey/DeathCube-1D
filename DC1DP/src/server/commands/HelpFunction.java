package server.commands;

import java.util.HashMap;

import server.player.Player;

public class HelpFunction implements ICommand{
	
	private HashMap<String, ICommand> _cmds;
	
	public static String COMMAND = "HELP";
	
	public HelpFunction(HashMap<String, ICommand> cmds) {
		// TODO Auto-generated constructor stub
		_cmds = cmds;
	}
	
	@Override
	public void performCommand(Player p, String[] args) {
		// TODO Auto-generated method stub
		if(args.length < 2){
			for(ICommand cf : _cmds.values()){
				p.appendToLog(cf.getCommandName());
			}
		}else{
			if(_cmds.containsKey(args[1])){
				p.appendToLog(_cmds.get(args[1]).getHelp());
			}else{
				p.appendToLog("Invalid command! Try HELP");
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
		return "Type HELP to get a list of commands. \n Type HELP COMMANDNAME to get help for that command.";
	}

}
