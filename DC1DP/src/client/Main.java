package client;

import java.io.IOException;

import shared.CouldNotConnect;

public class Main {
	public static void main(String[] args) {
		if(args.length < 3){
			System.err.println("Usage: NAME IP PORT");
			System.exit(-1);
		}
		
		try {
			//args: NAME [0] IP [1] PORT [2]
			ServerModule sm = new ServerModule(args[1], Integer.parseInt(args[2]),args[0]);
			
			//this thread is for the REPL (output thread)
			Thread t = new Thread(new REPL(sm));
			
			//this thread listens for server output (input thread)
			Thread t2 = new Thread(new ServerListener(sm));
			t.start();
			t2.start();
			
		} catch (IOException | CouldNotConnect e) {
			System.err.println("Connection halted! Did the server shut down unexpectedly or is your IP or PORT wrong?");
			System.exit(-1);
		}
	}
}
