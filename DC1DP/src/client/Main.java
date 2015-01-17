package client;

import java.io.IOException;

import shared.CouldNotConnect;

public class Main {
	public static void main(String[] args) {
		if(args.length < 3){
			System.err.println("Please enter a name! Form: NAME IP PORT");
			System.exit(-1);
		}
		
		try {
			ServerModule sm = new ServerModule(args[1], Integer.parseInt(args[2]),args[0]);
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
