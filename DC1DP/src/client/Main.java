package client;

import java.io.IOException;

import shared.CouldNotConnect;

public class Main {
	public static void main(String[] args) {
		if(args.length < 1){
			System.err.println("Please enter a name!");
			System.exit(-1);
		}
		
		try {
			ServerModule sm = new ServerModule("localhost", 30304,args[0]);
			Thread t = new Thread(new REPL(sm));
			Thread t2 = new Thread(new ServerListener(sm));
			t.start();
			t2.start();
		} catch (IOException | CouldNotConnect e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
