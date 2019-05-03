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
import java.net.UnknownHostException;
public class BroadcastServer extends Thread {
	DatagramSocket socket;
	DatagramPacket sendPacket;
	public BroadcastServer() {
		serverInput input = new serverInput();
		serverOut2 output2 = new serverOut2(input);
		start();
		input.start();
	}
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
		        //System.out.println(getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
		        //System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));
		
		        //See if the packet holds the right command (message)
		        String message = new String(packet.getData()).trim();
		        byte[] sendData = "Game".getBytes();
		        if (message.equals("Game")) {
		          //Send a response
		          sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
		          socket.send(sendPacket);
		          System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
		          System.out.println("####################################");
		        }
		      }
	    } catch (IOException x) {
	    }
	  }
}
class serverInput extends Thread{
	ServerSocket serverSocket;
	BomberData chatIn;
	boolean fig = false;
	int i=0;
	BomberData player[] = new BomberData[2];
	public serverInput() {
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
				chatIn = (BomberData) si.readObject();
				System.out.println(chatIn.getIP()+"\t\""+chatIn.getPort());
		          if(i==0) {
		        	  player[i]=chatIn;
		        	  player[i].setPi(i);
		        	  i=1;
		          }
		          /*else if(i==1) {
		        	  IP[i]=chatIn.getIP();
		        	  port[i]=chatIn.getPoet();
		        	  i=2; 
		          }
		          else if(i==2) {
		        	  IP[i]=chatIn.getIP();
		        	  port[i]=chatIn.getPoet();
		        	  i=3; 
		          }*/
		          else if(i==1) {
		        	  player[i]=chatIn;
		        	  player[i].setPi(i);
		        	  i=2;
		        	  for (int i = 0; i < player.length; i++) {
							System.out.println("IP"+i+" = "+player[i].getIP()+"   Port = "+player[i].getPort()+player[i].getPi());
			        	  }serverOut2 out2 = new serverOut2(this);		  
		          }
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
 	}
}
class serverOut2{
	serverInput input;
	int j=0;
	public serverOut2(serverInput input){
		this.input = input;
			if(j==0) {
			for (int i = 0; i < 2; i++) {
				try {
					try {
						this.input.player[i].setIpserver(InetAddress.getLocalHost().getHostAddress());
					} catch (UnknownHostException e1) {}
						this.input.player[i].setPi((i+1));
						this.input.player[i].setStartGame(true);
					byte[] serializedobject = new byte[2048];
					ByteArrayOutputStream bo = new ByteArrayOutputStream();
					ObjectOutputStream so = new ObjectOutputStream(bo);
					so.writeObject(this.input.player[i]);
					so.flush();
					serializedobject = bo.toByteArray();
					Socket socket2 = new Socket(this.input.player[i].getIP(),9990);
					PrintStream dataout = new PrintStream(socket2.getOutputStream());
					dataout.write(serializedobject);
					dataout.close();
				}catch (Exception e) {}
			}j=1;
			if(j==1) {
				try {
					byte[] serializedobject = new byte[2048];
					ByteArrayOutputStream bo = new ByteArrayOutputStream();
					ObjectOutputStream so = new ObjectOutputStream(bo);
					for (int i = 0; i < this.input.player.length; i++) {
						so.writeObject(this.input.player[i]);
						so.flush();
						serializedobject = bo.toByteArray();
						Socket socket2 = new Socket(this.input.player[i].getIP(),this.input.player[i].getPort());
						PrintStream dataout = new PrintStream(socket2.getOutputStream());
						dataout.write(serializedobject);
						dataout.close();
					}
				}catch (Exception e) {}
			}
			}
		}
}
