package client;

import java.io.IOException;

public class ServerListener implements Runnable{

	private ServerModule _sm;
	
	public ServerListener(ServerModule sm) {
		// TODO Auto-generated constructor stub
		_sm = sm;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				System.out.println(_sm.readFromServer().getData());
			
				synchronized (_sm) {
					_sm.notifyAll();
				}
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}

}
