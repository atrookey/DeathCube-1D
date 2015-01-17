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
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("> Error!");
				}
			}
		}
		
		scanner.close();
	}

}
