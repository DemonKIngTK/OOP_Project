import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverGame extends Thread{
	public static void main(String[] args) {
		serverGame run = new serverGame(new BomberData());
		run.start();
	}
	BomberData chatIn;
	public serverGame(BomberData chatIn) {
		this.chatIn = chatIn;
	}
	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(8888);
			while (true) {
				Socket socket = serverSocket.accept();
				InputStream input = socket.getInputStream();
				byte[] data = new byte[2048];
				input.read(data);
				ByteArrayInputStream bi = new ByteArrayInputStream(data);
				ObjectInputStream si = new ObjectInputStream(bi);
				chatIn = (BomberData) si.readObject();
				serverOutputTest outputTest = new serverOutputTest(chatIn);
			}
		} catch (Exception e) {}
	}
}
class serverOutputTest{
	BomberData chat;
	public serverOutputTest(BomberData chat) {
		this.chat = chat;
		byte[] data = new byte[2048];
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream so;
		try {
			chat.setPi(1);
			so = new ObjectOutputStream(bo);
			so.writeObject(this.chat);
			so.flush();
			data = bo.toByteArray();
			Socket socket = new Socket("10.160.61.112", 9990);
			PrintStream dataOut = new PrintStream(socket.getOutputStream());
			dataOut.write(data);
			dataOut.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
