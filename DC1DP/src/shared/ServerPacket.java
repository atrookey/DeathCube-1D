package shared;

import java.io.Serializable;

public class ServerPacket implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8163925141596883095L;
	
	private final String data;

	/**
	 * 
	 * @param data the data to be sent to the player
	 * @param ID i dunno really. Use the other constructor.
	 * @deprecated
	 */
	public ServerPacket(String data, String ID) {
		this(data);
	}
	
	/**
	 * @param data to be sent to the player. It is immutable.
	 */
	public ServerPacket(String data){
		this.data = data;
	}

	public String getData() {
		return data;
	}
}
