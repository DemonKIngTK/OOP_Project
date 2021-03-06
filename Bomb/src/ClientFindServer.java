import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


public class ClientFindServer {
	MyFrame frame;
	public ClientFindServer(MyFrame frame) {
		this.frame =frame;
		inputClient input = new inputClient(this.frame);
		MyClient client = new MyClient();
		input.start();
	}
}
class MyClient {
	DatagramSocket c ;
	DatagramPacket receivePacket;
	public MyClient(){
		try {
		  //Open a random port to send the package
		  c = new DatagramSocket();
		  c.setBroadcast(true);
		  byte[] sendData = "Game".getBytes();
		  //Try the 255.255.255.255 first
		  try {
		    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("255.255.255.255"), 8888);
		    c.send(sendPacket);
		    System.out.println(">>> Request packet sent to: 255.255.255.255 (DEFAULT)");
		  } catch (Exception e) {}
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
		      System.out.println( ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
		    }
		  }
		  System.out.println( ">>> Done looping over all network interfaces. Now waiting for a reply!");
		  //Wait for a response
		  byte[] recvBuf = new byte[15000];
		  receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
		  c.receive(receivePacket);
		  //We have a response
		  System.out.println( ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());
		  c.close();
		} catch (IOException ex) {
		  //Logger.getLogger(LoginWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		//System.out.println(receivePacket.getAddress().getHostAddress());
		BomberData chat = new BomberData();
		try {
			chat.setIP(InetAddress.getLocalHost().getHostAddress());
			chat.setPort(9990);
		} catch (UnknownHostException e1) {}
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
			System.out.println(e.getMessage());
		}
	}
}
class inputClient extends Thread{
	ServerSocket clientSocket;
	BomberData chatIn;
	MyFrame frame;
	BomberData chat = new BomberData();
	int i=0;
	public inputClient(MyFrame frame) {
		this.frame = frame;
	}
	@Override
	public void run() {
		super.run();
		try {
			clientSocket = new ServerSocket(9990);
			while (true) {
				Socket socket = clientSocket.accept();
				InputStream input = socket.getInputStream();
				byte[] data = new byte[2048];
				input.read(data);
				ByteArrayInputStream bi = new ByteArrayInputStream(data);
				ObjectInputStream si = new ObjectInputStream(bi);
				chatIn = (BomberData) si.readObject();
				System.out.println(chatIn.getIpserver()+"\t"+chatIn.getIP()+"\t"+chatIn.getPi()+"\t"+chatIn.getPort()+"\n");
				chat.setPlayer(chatIn.getPlayer());
				chat.setIpserver(chatIn.getIpserver());
				if(chatIn.isStart()) {
					frame.address=chatIn.getIP();
					frame.btnStart.setVisible(true);
				}
				else if(chatIn.isStartGame()){
					if(i==0) {
						bmb bmb = new bmb();
						
						i++;
					}
					
				}
				
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
class outClient  implements KeyListener{
	BomberData chat;
	bmb game;
	public outClient(bmb game) {
		this.game = game;
		BomberData chat = new BomberData();
		byte[] data = new byte[2048];
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream so;
		try {
			so = new ObjectOutputStream(bo);
			so.writeObject(chat);
			so.flush();
			data = bo.toByteArray();
			Socket socket = new Socket(chat.getIpserver(), 9999);
			PrintStream dataOut = new PrintStream(socket.getOutputStream());
			dataOut.write(data);
			dataOut.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT) {
			chat.setRight(true);
		}
		if(key==KeyEvent.VK_LEFT) {
			chat.setLeft(true);
		}	
		if(key==KeyEvent.VK_DOWN) {
			chat.setDown(true);
		}
		if(key==KeyEvent.VK_UP) {
			chat.setUp(true);
		}
		if(key==KeyEvent.VK_SPACE) {
			chat.setSpace(true);
		}
		outClient client = new outClient(game);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT) {
			chat.setRight(false);
		}
		if(key==KeyEvent.VK_LEFT) {
			chat.setLeft(false);
		}	
		if(key==KeyEvent.VK_DOWN) {
			chat.setDown(false);
		}
		if(key==KeyEvent.VK_UP) {
			chat.setUp(false);
		}
		if(key==KeyEvent.VK_SPACE) {
			chat.setSpace(false);
		}
		outClient client = new outClient(game);
	}
}
