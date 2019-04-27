import java.io.Serializable;

public class BomberData implements Serializable{
	private static final long serialVersionUID = 1L;
	//To join Server
	private String IP;
	private String name;
	private String player;
	private int poet;
	private String ipserver;
	private boolean start=false;
	private boolean startGame=false;
	
	//Chat
	private  String str;
	
	//Atibies
	private  int Pi;
	private  int power;
	private  int boom;
	
	//Move
	private  int corx;
	private  int cory;
	
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
	public int getPoet() {
		return poet;
	}
	public void setPoet(int poet) {
		this.poet = poet;
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
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getBoom() {
		return boom;
	}
	public void setBoom(int boom) {
		this.boom = boom;
	}
	public String getIpserver() {
		return ipserver;
	}
	public void setIpserver(String ipserver) {
		this.ipserver = ipserver;
	}
	public int getCorx() {
		return corx;
	}
	public void setCorx(int corx) {
		this.corx = corx;
	}
	public int getCory() {
		return cory;
	}
	public void setCory(int cory) {
		this.cory = cory;
	}
}
