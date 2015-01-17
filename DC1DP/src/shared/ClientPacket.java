package shared;

import java.io.Serializable;

public class ClientPacket implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2614233327951610682L;

	private final String data;
	
	private final String ID;
	
	public ClientPacket(String data, String ID) {
		this.data = data;
		this.ID = ID;
	}
	
	public String getData(){
		return data;
	}
	
	public String getID(){
		return ID;
	}
	
}
