package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import shared.ClientPacket;
import shared.CouldNotConnect;
import shared.ServerPacket;

public class ServerModule implements ServerInterface{

	private ObjectOutputStream _oos;
	private ObjectInputStream _ois;
	private Socket s;
	
	public ServerModule(String addr, int port, String args) throws UnknownHostException, IOException, CouldNotConnect {
		s = new  Socket(addr, port);
		
		_oos = new ObjectOutputStream(s.getOutputStream());
		
		_oos.writeObject(new ClientPacket("LOGON", args));
		_ois = new ObjectInputStream(s.getInputStream());
		
		try {
			ServerPacket sp = (ServerPacket) _ois.readObject();
			
			if(sp.getData().equals("OKAY")){
				System.out.println("OKAY!");
			}else{
				_oos.close();
				_ois.close();
				throw new CouldNotConnect();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void writeToServer(ClientPacket p) throws IOException {
		// TODO Auto-generated method stub
		_oos.writeObject(p);
	}

	@Override
	public ServerPacket readFromServer() throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		return (ServerPacket)_ois.readObject();
	}
	
	public void cleanUp(){
		try {
			_oos.close();
		} catch (IOException e) {
			System.err.println("Could not close _oos!");
			e.printStackTrace();
		}
		
		try {
			_ois.close();
		} catch (IOException e) {
			System.err.println("Could not close _ois!");
			e.printStackTrace();
		}
		
		try {
			s.close();
		} catch (IOException e) {
			System.err.println("Could not close s!");
			e.printStackTrace();
		}
	}
}
