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
import java.net.ServerSocket;
import java.net.Socket;
public class BroadcastServer extends Thread {
	public BroadcastServer() {
		serverInput input = new serverInput(this);
		serverOut output = new serverOut(this,input);
		start();
		input.start();
		output.start();
	}
	DatagramSocket socket;
	DatagramPacket sendPacket;
	  @Override
	  public void run() {
	    try {
	    	 //Keep a socket open to listen to all the UDP trafic that is destined for this port
	    	socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
	    	socket.setBroadcast(true);
	  	      while (true) {
	  	    	System.out.println(getClass().getName() + ">>>Ready to receive broadcast packets!");
		        //Receive a packet
		        byte[] recvBuf = new byte[15000];
		        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
		        socket.receive(packet);
		        //Packet received
		        System.out.println(getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
		        System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));
		
		        //See if the packet holds the right command (message)
		        String message = new String(packet.getData()).trim();
		        byte[] sendData = "Game".getBytes();
		        if (message.equals("Game")) {
		          //Send a response
		          sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
		          socket.send(sendPacket);
		          System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
		        }
		      }
	    } catch (IOException x) {
	    }
	  }
}
class serverInput extends Thread{
	BroadcastServer server;
	ServerSocket serverSocket;
	BomberData bomdata;
	boolean fig = false;
	int i=0;
	String IP[] = new String[4];
	int port[] = new int[4];
	public serverInput(BroadcastServer server) {
		this.server = server;
	}
	@Override
	public void run() {
		super.run();
		try {
			serverSocket = new ServerSocket(8888);
			while (true) {
				Socket socket = serverSocket.accept();
				InputStream input = socket.getInputStream();
				byte[] data = new byte[2048];
				input.read(data);
				ByteArrayInputStream bi = new ByteArrayInputStream(data);
				ObjectInputStream si = new ObjectInputStream(bi);
				bomdata = (BomberData) si.readObject();
				System.out.println(bomdata.getIp()+"\t\""+bomdata.getPort());
		          if(i==0) {
		        	  IP[i]=bomdata.getIp();
		        	  port[i]=bomdata.getPort();
		        	  i=1;
		          }
		          else if(i==1) {
		        	  IP[i]=bomdata.getIp();
		        	  port[i]=bomdata.getPort();
		        	  i=2; 
		          }
		          else if(i==2) {
		        	  IP[i]=bomdata.getIp();
		        	  port[i]=bomdata.getPort();
		        	  i=3; 
		          }
		          else if(i==3) {
		        	  IP[i]=bomdata.getIp();
		        	  port[i]=bomdata.getPort();
		        	  i=4;
		        	  for (int i = 0; i < IP.length; i++) {
							System.out.println("IP"+i+" = "+IP[i]+"   Port = "+port[i]);
			        	  }fig=true;
		          }
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
 	}
}
class serverInputData extends Thread{
	BroadcastServer server;
	ServerSocket serverSocket;
	BomberData dataBom;
	public serverInputData(BroadcastServer server) {
		this.server = server;
	}
	@Override
	public void run() {
		super.run();
		try {
			serverSocket = new ServerSocket(8888);
			while (true) {
				Socket socket = serverSocket.accept();
				InputStream input = socket.getInputStream();
				byte[] data = new byte[4000];
				input.read(data);
				ByteArrayInputStream bi = new ByteArrayInputStream(data);
				ObjectInputStream si = new ObjectInputStream(bi);
				dataBom = (BomberData) si.readObject();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
 	}
}
class serverOut extends Thread{
	BroadcastServer server;
	ServerSocket serverSocket;
	serverInput input;
	int j=1;
	public serverOut(BroadcastServer server,serverInput input) {
		this.server = server;
		this.input = input;
	}
	@Override
	public void run() {
		super.run();
		while (true) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {}
			while (input.fig) {
				MessgeChat chat = new MessgeChat();
				if(j==1) {
					chat.setPlayer("Player"+j);
					j=2;
				}
				else if(j==2) {
					chat.setPlayer("Player"+j);
					j=3;
				}
				else if(j==3) {
					chat.setPlayer("Player"+j);
					j=4;
				}
				else if(j==4) {
					chat.setPlayer("Player"+j);
					j=5;
				}
				chat.setIP("^_^");
				try {
					byte[] serializedobject = new byte[2048];
					ByteArrayOutputStream bo = new ByteArrayOutputStream();
					ObjectOutputStream so = new ObjectOutputStream(bo);
					so.writeObject(chat);
					so.flush();
					serializedobject = bo.toByteArray();
					for (int i = 0; i < input.IP.length; i++) {
						Socket socket2 = new Socket(input.IP[i],input.port[i]);
						PrintStream dataout = new PrintStream(socket2.getOutputStream());
						dataout.write(serializedobject);
						dataout.close();
					}
					input.fig=false;
				} catch (Exception e) {}
			}
			
		}
		
	}
}
