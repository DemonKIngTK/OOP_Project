import java.io.Serializable;

public class MessgeChat implements Serializable{
	private static final long serialVersionUID = 1234567;
	private String IP;
	private String name;
	private String player;
	private int poet;
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public int getPoet() {
		return poet;
	}
	public void setPoet(int poet) {
		this.poet = poet;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
