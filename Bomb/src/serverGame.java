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
			ServerSocket serverSocket = new ServerSocket(9999);
			while (true) {
				System.out.println(555);
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
	Socket socket2;
	public serverOutputTest(BomberData chat) {
		this.chat = chat;
		
		try {
			for (int i = 0; i < 2; i++) {
				byte[] data = new byte[2048];
				ByteArrayOutputStream bo = new ByteArrayOutputStream();
				ObjectOutputStream so =new ObjectOutputStream(bo);
				so.writeObject(chat);
				so.flush();
				data = bo.toByteArray();
				if(i==0) {
					socket2 = new Socket("192.168.43.106",10000);
				}else {
					socket2 = new Socket("192.168.43.204",10000);
				}
				
				PrintStream dataout = new PrintStream(socket2.getOutputStream());
				dataout.write(data);
				dataout.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
