package server;

import java.io.IOException;

public class ServerMain {
	public static void main(String[] args) {
		if(args.length < 1){
			System.err.println("Usage: PORT");
		}else{
		
			try {
				new Server(Integer.parseInt(args[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
