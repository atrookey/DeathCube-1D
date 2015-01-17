package client;

import java.io.IOException;

import shared.ClientPacket;
import shared.ServerPacket;

public interface ServerInterface {
	
	public void writeToServer(ClientPacket p) throws IOException;
	
	public ServerPacket readFromServer() throws ClassNotFoundException, IOException;

}
