package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import server.commands.CommandFunction;
import server.commands.GoFunction;
import server.commands.LookFunction;
import shared.ServerPacket;

public class Server {

	private ArrayList<PlayerConnection> _connectedPlayers;

	private HashMap<Player, PlayerConnection> _players;
	private HashMap<String, CommandFunction> _commands;
	
	private Cube _cube;

	private Object _lock;

	private static Server _server;
	
	public Server(int port) throws IOException {
		_connectedPlayers = new ArrayList<>();

		_players = new HashMap<>();
		_commands = new HashMap<>();

		_lock = new Object();
		
		_cube = new Cube(2, 2, 2);

		registerCommands(_commands);
		
		_server = this;

		Thread t = new Thread(new ConnectionListener(this, port));
		t.start();

	}

	private class ConnectionListener implements Runnable {

		private Server _s;
		private int p;

		public ConnectionListener(Server s, int port) {
			// TODO Auto-generated constructor stub
			_s = s;
			p = port;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

			while (true) {
				Socket s;
				try {
					s = ss.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}

				PlayerConnection pc = null;
				try {
					pc = new PlayerConnection(s, _s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}

				Thread t = new Thread(pc);
				t.start();

				_connectedPlayers.add(pc);
			}
		}

	}

	public void notifyPlayers(String notification) {
		for (PlayerConnection pc : _connectedPlayers) {
			try {
				pc.notifyPlayer(new ServerPacket(notification, null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("Could not send to player!");
			}
		}
	}
	
	public void notifyPlayer(Player p,String s){
		if(_players.containsKey(p)){
			try {
				_players.get(p).notifyPlayer(new ServerPacket(s,null));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void parseInput(String input, Player p) {
		synchronized (_lock) {
			input = input.toUpperCase();

			String[] args = input.split(" ");

			System.out.println(args);

			if (args.length < 1 || !_commands.containsKey(args[0])) {
				System.out.println();
				p.appendToLog("Invalid command! Type help for more info.");
			} else {
				_commands.get(args[0]).performCommand(p, args);
			}
		}
	}

	public void registerPlayer(Player p, PlayerConnection pc) {
		_players.put(p, pc);
	}

	private void registerCommands(HashMap<String, CommandFunction> cmds) {
		cmds.put("GO", new GoFunction());
		cmds.put("LOOK", new LookFunction());
	}
	
	public Cube getCube(){
		return _cube;
	}

	public static Server getServer(){
		return _server;
	}
}
