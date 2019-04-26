import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClientRecieve extends Thread{
	MyFrame frame;
	DatagramSocket socket;
	BomberData player;
	public ClientRecieve(MyFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame=frame;
	}
	@Override
	public void run() {
		
		try {
			while (true) {
				byte[] data=new byte[2048];
				DatagramPacket packet=new DatagramPacket(data, data.length);
				socket.receive(packet);
				
				ByteArrayInputStream bi=new ByteArrayInputStream(data);
				ObjectInputStream si=new ObjectInputStream(bi);
				player=(BomberData)si.readObject();
				
				frame.ChatArea.append(player.getName()+player.getStr());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
