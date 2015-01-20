package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import server.commands.CommandFunction;
import server.commands.GoFunction;
import server.commands.HelpFunction;
import server.commands.LookFunction;
import server.commands.MapFunction;
import server.commands.SayFunction;
import shared.ServerPacket;

public class Server {

	private ArrayList<PlayerConnection> _connectedPlayers;

	private HashMap<Player, PlayerConnection> _players;
	private HashMap<String, CommandFunction> _commands;
	
	private Cube _cube;

	//This lock is to make player actions synchronous and to eliminate any race conditions
	private Object _lock;

	
	public Server(int port) throws IOException {
		_connectedPlayers = new ArrayList<>();

		//allows for player connection look up by player. Unused now?
		_players = new HashMap<>();
		_commands = new HashMap<>();

		_lock = new Object();
		
		_cube = new Cube(3, 3, 3);

		registerCommands(_commands);
		

		Thread t = new Thread(new ConnectionListener(this, port));
		t.start();
		
		Thread t2 = new Thread(new CubeShifter());
		t2.start();

	}

	/**
	 * 
	 * @author ginonott
	 * This class simply sits on a thread and listens for connections on it's port. It then creates player connections.
	 */
	private class ConnectionListener implements Runnable {

		private Server _s;
		private int p;

		public ConnectionListener(Server s, int port) {
			// TODO Auto-generated constructor stub
			_s = s;
			p = port;
		}

		@SuppressWarnings("resource")
		@Override
		public void run() {
			// TODO: Change this from a while(true) to something else. ss needs to be cleaned up.
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
	
	/**
	 * There is a lot wrong with this.
	 * A) This should be an action listener and plugged into
	 * a TimerThread.
	 * B)This should really just be in the cube class.
	 * 
	 * @author ginonott
	 *
	 *TODO: FIX THIS
	 */
	private class CubeShifter implements Runnable{

		@Override
		public void run() {
			
			while(true){
				//this ensures that players cannot move through rooms while
				//the cube is in an inconsistent state.
				synchronized (_lock) {
					System.out.println("Shifting rooms!");
					_cube.shiftRooms();
					System.out.println("Rooms shifted!");
				}
				
				try {
					//30 seconds
					Thread.sleep(1000 * 30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * Notify all players.
	 * 
	 * @param notification the notification to be sent out to every player
	 * currently connected to this server.
	 */
	public void notifyPlayers(String notification) {
		for (PlayerConnection pc : _connectedPlayers) {
			try {
				pc.notifyPlayer(new ServerPacket(notification));
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Could not send to player!");
			}
		}
	}

	/**
	 * Parse the user's input. Perhaps this should not be in the server class
	 * and instead in the player connection class?
	 * 
	 * @param input the input from player
	 * @param p the player it originated from
	 */
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

	/**
	 * Register a player into the server. Should look to see if a player with that name is already
	 * taken but whatever. Later.
	 * 
	 * @param p the player to be logged on.
	 * @param pc the connection that is serving this player.
	 */
	public void registerPlayer(Player p, PlayerConnection pc) {
		_players.put(p, pc);
	}

	/**
	 * Bind commands to a map. This allows them to be easily called.
	 * @param cmds
	 */
	private void registerCommands(HashMap<String, CommandFunction> cmds) {
		cmds.put(GoFunction.COMMAND, new GoFunction());
		cmds.put(LookFunction.COMMAND, new LookFunction());
		cmds.put(SayFunction.COMMAND, new SayFunction());
		cmds.put(MapFunction.COMMAND, new MapFunction(this));
		cmds.put(HelpFunction.COMMAND, new HelpFunction(cmds));
	}
	
	/**
	 * @return this server's cube.
	 */
	public Cube getCube(){
		return _cube;
	}
}
