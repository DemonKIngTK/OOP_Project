import java.io.Serializable;

public class MessgeChat implements Serializable{
	private static final long serialVersionUID = 1234567;
	private String IP;
	private String name;
	private String player;
	private int poet;
	private boolean start=false;
	private boolean startGame=false;
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
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean isStartGame() {
		return startGame;
	}
	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}
}
