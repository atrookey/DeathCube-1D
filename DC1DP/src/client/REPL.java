package client;

import java.io.IOException;
import java.util.Scanner;

import shared.ClientPacket;

public class REPL implements Runnable{

	private ServerModule _sm;
	
	public REPL(ServerModule sm) {
		_sm = sm;
	}
	
	@Override
	public void run() {
		//bind to the console input
		Scanner scanner = new Scanner(System.in);
		
		String line = null;
		
		while(true){
			line = scanner.nextLine();
			
			if(line.equals("quit")){
				break;
			}else{
				try {
					_sm.writeToServer(new ClientPacket(line, "GINO"));
				} catch (IOException e ) {
					System.out.println("Could not write to the server!!");
					System.exit(-1);
				}
			}
		}
		
		//clean up our resources like a good program :-)
		scanner.close();
		_sm.cleanUp();
		System.exit(0);
	}

}
