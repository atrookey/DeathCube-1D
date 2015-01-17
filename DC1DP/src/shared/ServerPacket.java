package shared;

import java.io.Serializable;

public class ServerPacket implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8163925141596883095L;
	
	private final String data;

	public ServerPacket(String data, String ID) {
		this.data = data;
	}

	public String getData() {
		return data;
	}
}
