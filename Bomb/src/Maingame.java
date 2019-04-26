import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Maingame {
	public static void main(String[] args) {
		MyFrame Login=new MyFrame("Login");
		MyFrame Lobby=new MyFrame();
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Login.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Login.setVisible(false);
				Lobby.setVisible(true);
			}
		});
		Login.buttonExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				System.exit(0);
				
			}
		});
		Lobby.btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Lobby.setVisible(false);
				Login.setVisible(true);
			}
		});
		
	}
}
class MyFrame extends JFrame{
	JTextField textField = new JTextField();
	JButton btnExit = new JButton("Exit");
	JLabel lblNewLabel = new JLabel("");
	JTextArea ChatArea = new JTextArea();
	JLabel lblHost = new JLabel("Host");
	JLabel LabelHost = new JLabel("");
	JLabel lblPlayer = new JLabel("Player");
	JLabel labelP1 = new JLabel("");
	JLabel labelP2 = new JLabel("");
	JLabel labelP3 = new JLabel("");
	JTextField textField_1 = new JTextField();
	JTextField textField_2 = new JTextField();
	JLabel lblHostIp = new JLabel("Host IP");
	JButton btnOk = new JButton("OK");
	JButton buttonExit = new JButton("Exit");
	JButton buttonStart = new JButton("Start");
	JLabel lblName = new JLabel("Name");
	public MyFrame() {
		setBounds(100, 100, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.CYAN);
		setTitle("Lobby");
		getContentPane().setLayout(null);
				
		textField.setBounds(10, 372, 500, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
				
		btnExit.setBounds(220, 403, 89, 23);
		getContentPane().add(btnExit);
				
		lblNewLabel.setBackground(Color.red);
		lblNewLabel.setBounds(520, 25, 30, 30);
		getContentPane().add(lblNewLabel);
				
		ChatArea.setBounds(10, 11, 500, 350);
		getContentPane().add(ChatArea);
				
		lblHost.setBounds(630, 16, 46, 14);
		getContentPane().add(lblHost);
				
		LabelHost.setBounds(520, 41, 254, 23);
		getContentPane().add(LabelHost);
				
		lblPlayer.setBounds(630, 75, 46, 14);
		getContentPane().add(lblPlayer);
		
		labelP1.setBounds(520, 100, 254, 23);
		getContentPane().add(labelP1);
				
		labelP2.setBounds(520, 134, 254, 23);
		getContentPane().add(labelP2);
				
		labelP3.setBounds(520, 168, 254, 23);
		getContentPane().add(labelP3);
		
	}
	MyFrame(String title){
		setBounds(100, 100, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.CYAN);
		getContentPane().setLayout(null);
				
		lblName.setBounds(230, 39, 46, 14);
		getContentPane().add(lblName);
				
		textField_1.setBounds(89, 61, 305, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
				
		textField_2.setBounds(89, 130, 305, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblHostIp.setBounds(230, 105, 46, 14);
		getContentPane().add(lblHostIp);
		
		btnOk.setBounds(89, 200, 89, 23);
		getContentPane().add(btnOk);

		buttonExit.setBounds(305, 200, 89, 23);
		getContentPane().add(buttonExit);
		
		buttonStart.setBounds(305, 200, 89, 23);
		getContentPane().add(buttonStart);
		setVisible(true);
	}
}
