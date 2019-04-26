import java.awt.TextArea;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import javax.swing.JFrame;
public class Clienttest {
	public static void main(String[] args) {
		MyFreams fream = new MyFreams();
		inputClient1 input = new inputClient1(fream);
		fream.setVisible(true);
		MyClientsa client = new MyClientsa(fream,input);
		input.start();
		
	}
}
class MyFreams extends JFrame{
	TextArea area = new TextArea();
	public MyFreams() {
		setSize(720, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		area.setBounds(0, 0, 700, 500);
		
		add(area);
	}
}
class MyClientsa {
	DatagramSocket c ;
	DatagramPacket receivePacket;
	MyFreams fream;
	inputClient1 client1;
	public MyClientsa(MyFreams fream,inputClient1 client1){
		this.fream = fream;
		this.client1 = client1;
		try {
		  //Open a random port to send the package
		  c = new DatagramSocket();
		  c.setBroadcast(true);

		  byte[] sendData = "Game".getBytes();

		  //Try the 255.255.255.255 first
		  try {
		    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
		    c.send(sendPacket);
		    fream.area.append(">>> Request packet sent to: 255.255.255.255 (DEFAULT)\n");
		  } catch (Exception e) {
		  }

		  // Broadcast the message over all the network interfaces
		  Enumeration interfaces = NetworkInterface.getNetworkInterfaces();
		  while (interfaces.hasMoreElements()) {
		    NetworkInterface networkInterface = (NetworkInterface)interfaces.nextElement();

		    if (networkInterface.isLoopback() || !networkInterface.isUp()) {
		      continue; // Don't want to broadcast to the loopback interface
		    }

		    for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
		      InetAddress broadcast = interfaceAddress.getBroadcast();
		      if (broadcast == null) {
		        continue;
		      }

		      // Send the broadcast package!
		      try {
		        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, broadcast, 8888);
		        c.send(sendPacket);
		      } catch (Exception e) {
		      }
		      fream.area.append( ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName()+"\n");
		    }
		  }
		  fream.area.append( ">>> Done looping over all network interfaces. Now waiting for a reply!\n");
		  //Wait for a response
		  byte[] recvBuf = new byte[15000];
		  receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
		  c.receive(receivePacket);
		  //We have a response
		  fream.area.append( ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress()+"\n");
		  
//		  //Check if the message is correct
//		  String message = new String(receivePacket.getData()).trim();
//		  if (message.equals("Game")) {
		    //DO SOMETHING WITH THE SERVER'S IP (for example, store it in your controller)
//			  fream.area.append("Helle Word!!\n");
			 // System.out.println( ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());
		    //Controller_Base.setServerIp(receivePacket.getAddress());
//		  }
		  //Close the port!
		  c.close();
		} catch (IOException ex) {
		  //Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		MessgeChat chat = new MessgeChat();
		try {
			chat.setIP(InetAddress.getLocalHost().getHostAddress());
			fream.setTitle("Client1"+InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		chat.setPoet(9980);
		byte[] data = new byte[2048];
		
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream so;
		try {
			so = new ObjectOutputStream(bo);
			so.writeObject(chat);
			so.flush();
			data = bo.toByteArray();
			Socket socket = new Socket(receivePacket.getAddress().getHostAddress(), 8888);
			PrintStream dataOut = new PrintStream(socket.getOutputStream());
			dataOut.write(data);
			dataOut.close();
		} catch (IOException e) {
			fream.area.append(e.getMessage()+"\n");
		}
	}
}class inputClient1 extends Thread{
	MyFreams fream;
	ServerSocket clientSocket;
	public inputClient1(MyFreams fream) {
		this.fream = fream;
	}
	@Override
	public void run() {
		super.run();
		try {
			clientSocket = new ServerSocket(9980);
			while (true) {
				Socket socket = clientSocket.accept();
				InputStream input = socket.getInputStream();
				byte[] data = new byte[2048];
				input.read(data);
				ByteArrayInputStream bi = new ByteArrayInputStream(data);
				ObjectInputStream si = new ObjectInputStream(bi);
				MessgeChat chatIn = (MessgeChat) si.readObject();
				fream.area.append(chatIn.getIP()+"\t"+chatIn.getPlayer()+"\n");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}