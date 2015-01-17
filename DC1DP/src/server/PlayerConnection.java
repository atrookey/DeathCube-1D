package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import shared.ClientPacket;
import shared.ServerPacket;

public class PlayerConnection implements Runnable {
	private Socket _socket;
	private Server _server;

	private ObjectInputStream _ois;
	private ObjectOutputStream _oos;
	
	private Player p;

	public PlayerConnection(Socket s, Server serv) throws IOException {
		// TODO Auto-generated constructor stub
		_socket = s;
		_server = serv;
																																																
		System.out.println("connected!");

		_ois = new ObjectInputStream(s.getInputStream());
		System.out.println("Reading object!");
		
		ClientPacket cp = null;
		
		try {
			cp = (ClientPacket) _ois.readObject();
			System.out.println("Message recieved!");
			System.out.println("Contents: " + cp.getData() + " : " + cp.getID());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Writing out...!");
		_oos = new ObjectOutputStream(s.getOutputStream());
		_oos.writeObject(new ServerPacket("OKAY", null));
		System.out.println("Object written!");

		p = new Player(cp.getID());
		
		_server.registerPlayer(p, this);
		
		_server.getCube().placePlayer(p);
	}
	
	synchronized public void notifyPlayer(ServerPacket sp) throws IOException{
		_oos.writeObject(sp);
		_oos.flush();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				ClientPacket cp = (ClientPacket) _ois.readObject();
				_server.parseInput(cp.getData(), p);
				String s = p.retrieveLog();
				_oos.writeObject(new ServerPacket(s, null));
				_oos.flush();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}

		}
	}

}
