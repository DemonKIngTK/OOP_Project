import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class guiFrame extends JFrame{
	BroadcastServer server;
	serverInput input;
	ClientFindServer client;
	inputClient client2;
	Back back=new Back();
	public guiFrame() {
		setSize(1000,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		add(back);
		setVisible(true);
	}
}

class Back extends JPanel {
	Image bg = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir")+File.separator+"bom.jpg");
	Button jion,startServer,ex;
	public Back() {
		setLayout(null);
		jion = new Button("Jion");
		jion.setBackground(Color.LIGHT_GRAY);
		jion.setBounds(500, 290, 250, 60);
		add(jion);
		startServer = new Button("StartServer");
		startServer.setBackground(Color.LIGHT_GRAY);
		startServer.setBounds(500, 370, 250, 60);
		add(startServer);
		ex = new Button("Exit");
		ex.setBackground(Color.LIGHT_GRAY);
		ex.setBounds(500, 450, 250, 60);
		add(ex);
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg,0,0,this);
		
	}
}
