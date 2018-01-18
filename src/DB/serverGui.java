package DB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import Matala_0.Line_46;
import gui.GUI;
import gui.Wrapper;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

public class serverGui {
	GUI gui;
	Wrapper wrap = new Wrapper();
	boolean flag = false;
	Set<Line_46> data = new HashSet<Line_46>();
	public JFrame frame;
	private JTextField ip;
	private JTextField port;
	private JTextField page;
	private JTextField user;
	private JTextField password;
	private JTextField tableName;
	private ConnectToServer cts = new ConnectToServer();
	private JTextField error;
	
	public ConnectToServer getCts() {
		return cts;
	}

	public void setCts(ConnectToServer cts) {
		this.cts = cts;
	}

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					serverGui window = new serverGui();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//	/**
//	 * Create the application.
//	 */
	public serverGui(Wrapper w) {
		wrap = w;
		initialize();
		
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 448, 321);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(!ip.getText().isEmpty() && !page.getText().isEmpty() && !password.getText().isEmpty() && !port.getText().isEmpty() && !tableName.getText().isEmpty() && !user.getText().isEmpty()) {
				    
					ConnectToServer.set_ip(ip.getText());
					ConnectToServer.set_page(page.getText());
					ConnectToServer.set_password(password.getText());
					ConnectToServer.set_port(port.getText());
					ConnectToServer.set_tableName(tableName.getText());
					ConnectToServer.set_user(user.getText());
					ConnectToServer.set_url();
					ConnectToServer.test_ex4_db();
					data.addAll(ConnectToServer.getData());
					wrap.setDataBase(data);
					gui.printStats();
					
				}else {
					error.setText("Missing input!");
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(141, 197, 100, 40);
		frame.getContentPane().add(btnNewButton);
		
		ip = new JTextField();
		ip.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ip.setBounds(112, 11, 239, 20);
		frame.getContentPane().add(ip);
		ip.setColumns(10);
		
		port = new JTextField();
		port.setFont(new Font("Tahoma", Font.PLAIN, 12));
		port.setBounds(112, 42, 239, 20);
		frame.getContentPane().add(port);
		port.setColumns(10);
		
		page = new JTextField();
		page.setFont(new Font("Tahoma", Font.PLAIN, 12));
		page.setBounds(112, 73, 239, 20);
		frame.getContentPane().add(page);
		page.setColumns(10);
		
		user = new JTextField();
		user.setFont(new Font("Tahoma", Font.PLAIN, 12));
		user.setBounds(112, 104, 239, 20);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		password = new JTextField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 12));
		password.setBounds(112, 138, 239, 20);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		tableName = new JTextField();
		tableName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableName.setBounds(112, 166, 239, 20);
		frame.getContentPane().add(tableName);
		tableName.setColumns(10);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(38, 17, 68, 14);
		frame.getContentPane().add(lblIp);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(38, 48, 68, 14);
		frame.getContentPane().add(lblPort);
		
		JLabel lblPage = new JLabel("Page");
		lblPage.setBounds(38, 79, 68, 14);
		frame.getContentPane().add(lblPage);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(38, 110, 68, 14);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(38, 141, 68, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblTableName = new JLabel("Table name");
		lblTableName.setBounds(38, 169, 76, 14);
		frame.getContentPane().add(lblTableName);
		
		error = new JTextField();
		error.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error.setEditable(false);
		error.setBounds(38, 248, 253, 20);
		frame.getContentPane().add(error);
		error.setColumns(10);
	}

	public Set<Line_46> getData() {
		return data;
	}

	public void setData(Set<Line_46> data) {
		this.data = data;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Wrapper getWrap() {
		return wrap;
	}

	public void setWrap(Wrapper wrap) {
		this.wrap = wrap;
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
}
