import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class demoframe {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demoframe window = new demoframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public demoframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.CYAN);
		frame.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(230, 39, 46, 14);
		frame.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 61, 305, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(89, 130, 305, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblHostIp = new JLabel("Host IP");
		lblHostIp.setBounds(230, 105, 46, 14);
		frame.getContentPane().add(lblHostIp);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(89, 200, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JButton buttonExit = new JButton("Exit");
		buttonExit.setBounds(305, 200, 89, 23);
		frame.getContentPane().add(buttonExit);
		
		
	}
}
