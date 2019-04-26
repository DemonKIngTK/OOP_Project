import java.io.Serializable;

public class BomberData implements Serializable{
	private static final long serialVersionUID = 1L;
	//To join Server
	private static String Name;
	private static String Ip;
	
	//Chat
	private static String str;
	
	//Atibies
	private static int Pi;
	private static int power;
	private static int boom;
	
	//Move
	private static boolean space;
	private static boolean up;
	private static boolean down;
	private static boolean right;
	private static boolean left;
	public static String getName() {
		return Name;
	}
	public static void setName(String name) {
		Name = name;
	}
	public static String getIp() {
		return Ip;
	}
	public static void setIp(String ip) {
		Ip = ip;
	}
	public static int getPower() {
		return power;
	}
	public static void setPower(int power) {
		BomberData.power = power;
	}
	public static int getBoom() {
		return boom;
	}
	public static void setBoom(int boom) {
		BomberData.boom = boom;
	}
	public static boolean isSpace() {
		return space;
	}
	public static void setSpace(boolean space) {
		BomberData.space = space;
	}
	public static boolean isUp() {
		return up;
	}
	public static void setUp(boolean up) {
		BomberData.up = up;
	}
	public static boolean isDown() {
		return down;
	}
	public static void setDown(boolean down) {
		BomberData.down = down;
	}
	public static boolean isRight() {
		return right;
	}
	public static void setRight(boolean right) {
		BomberData.right = right;
	}
	public static boolean isLeft() {
		return left;
	}
	public static void setLeft(boolean left) {
		BomberData.left = left;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getStr() {
		return str;
	}
	public static void setStr(String str) {
		BomberData.str = str;
	}
	public static int getPi() {
		return Pi;
	}
	public static void setPi(int pi) {
		Pi = pi;
	}
	
}
