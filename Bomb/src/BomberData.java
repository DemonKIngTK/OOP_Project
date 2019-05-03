import java.io.Serializable;

public class BomberData implements Serializable{
	private static final long serialVersionUID = 1L;
	//To join Server
	private String IP;
	private String name;
	private String player;
	private int port;
	private String ipserver;
	private boolean start=false;
	private boolean startGame=false;
	
	//Chat
	private  String str;
	
	//Atibies
	private  int Pi;
	private  int item;
	
	
	//Move
	private  boolean space;
	private  boolean up;
	private  boolean down;
	private  boolean right;
	private  boolean left;
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int poet) {
		this.port = port;
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
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getPi() {
		return Pi;
	}
	public void setPi(int pi) {
		Pi = pi;
	}
	public boolean isSpace() {
		return space;
	}
	public void setSpace(boolean space) {
		this.space = space;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public String getIpserver() {
		return ipserver;
	}
	public void setIpserver(String ipserver) {
		this.ipserver = ipserver;
	}
}
