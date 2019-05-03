import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements KeyListener{
	BomberData chat;
	bmb game;
	public Client(bmb game,BomberData chat) {
		this.game=game;
		this.chat = chat;
		inputClientTest test = new inputClientTest(this.game,this.chat);
		test.start();
		byte[] data = new byte[2048];
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream so;
		try {
			so = new ObjectOutputStream(bo);
			so.writeObject(this.chat);
			so.flush();
			data = bo.toByteArray();
			Socket socket = new Socket("192.168.43.106", 8890);
			PrintStream dataOut = new PrintStream(socket.getOutputStream());
			dataOut.write(data);
			dataOut.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
		@Override
		public void keyTyped(KeyEvent e) {
			
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
			Client client = new Client(this.game,chat);
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
			Client client = new Client(this.game,chat);
		}
}
class inputClientTest extends Thread{
	BomberData chatIn;
	bmb game;
	public inputClientTest(bmb game,BomberData chatIn) {
		this.chatIn = chatIn;
		this.game=game;
	}
	@Override
	public void run() {
		super.run();
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(10000);
			while (true) {	
				Socket socket = serverSocket.accept();
				InputStream input = socket.getInputStream();
				byte[] data = new byte[2048];
				input.read(data);
				ByteArrayInputStream bi = new ByteArrayInputStream(data);
				ObjectInputStream si = new ObjectInputStream(bi);
				chatIn = (BomberData) si.readObject();
				System.out.println("Player "+chatIn.getPi()+"Down="+chatIn.isDown()+"\nLeft="+chatIn.isLeft()+"\nRight="+chatIn.isRight()
										+"\nSpace="+chatIn.isSpace()+"\nUp="+chatIn.isUp());
				if(chatIn.getPi()==0) {
					if(chatIn.isRight()) {
						if(game.valid(game.y1,game.x1+1)==1)
						{
							game.xPixel1+=50;
							game.x1++;
							game.atrib(game.y1,game.x1,1);
						}
					}
					else if(chatIn.isLeft()) {
						if(game.valid(game.y1,game.x1-1)==1)
						{
							game.xPixel1-=50;
							game.x1--;
							game.atrib(game.y1,game.x1,1);
						}
					}else if(chatIn.isUp()) {
						if(game.valid(game.y1-1,game.x1)==1)
						{
							game.yPixel1-=50;
							game.y1--;
							game.atrib(game.y1,game.x1,1);
						}
					}else if(chatIn.isDown()) {
						if(game.valid(game.y1+1,game.x1)==1)
						{
							game.yPixel1+=50;
							game.y1++;
							game.atrib(game.y1,game.x1,1);
						}
					}else if(chatIn.isSpace()) {
						if(game.ok1==0&&game.bomb1==0)
						{game.ok1=1;
						game.cx=game.xPixel1+20;
						game.c1=game.x1;
						game.cy=game.yPixel1+10;
						game.c2=game.y1;
						game.v[game.c2][game.c1]=5;
						game.time1=System.nanoTime();}
					}
					}
				else if(chatIn.getPi()==1) {
					if(chatIn.isRight()) {
						if(game.valid(game.y2,game.x2+1)==1)
						{
							game.xPixel2+=50;
							game.x2++;
							game.atrib(game.y2,game.x2,1);
						}
					}
					else if(chatIn.isLeft()) {
						if(game.valid(game.y2,game.x2-1)==1)
						{
							game.xPixel2-=50;
							game.x2--;
							game.atrib(game.y2,game.x2,1);
						}
					}else if(chatIn.isUp()) {
						if(game.valid(game.y2-1,game.x2)==1)
						{
							game.yPixel2-=50;
							game.y2--;
							game.atrib(game.y2,game.x2,1);
						}
					}else if(chatIn.isDown()) {
						if(game.valid(game.y2+1,game.x2)==1)
						{
							game.yPixel2+=50;
							game.y2++;
							game.atrib(game.y2,game.x2,1);
						}
					}else if(chatIn.isSpace()) {
						if(game.ok2==0&&game.bomb2==0)
						{
							game.ok2=1;
							game.cx1=game.xPixel2+20;
							game.C1=game.x2;
							game.cy1=game.yPixel2+10;
							game.C2=game.y2;
							game.time2=System.nanoTime();
							game.v[game.C2][game.C1]=5;}
					}
					
					}
				game.repaint();
				}
				
			
		} catch (Exception e) {}
	}
}
