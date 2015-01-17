package server;

import java.io.IOException;

public class ServerMain {
	public static void main(String[] args) {
		try {
			new Server(30304);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
