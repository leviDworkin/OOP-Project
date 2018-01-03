package gui;
import matala_2.*;
import Matala_0.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;

public class GUI {

	private JFrame frame;
	private JTextField wigglePath;
	private Wrapper wrap = new Wrapper();
	private JButton writeKmlBtn;
	private JTextField comboPath;
	private JTextField recTextField;
	private JTextField routerTextField;
	private JTextField filterTextField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 665, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		wigglePath = new JTextField();
		wigglePath.setBounds(289, 11, 337, 20);
		wigglePath.setText("Enter folder path with wiggle csv's");
		frame.getContentPane().add(wigglePath);
		wigglePath.setColumns(10);
		
		JButton writeCsvBtn = new JButton("Write to csv");
		writeCsvBtn.setBounds(10, 10, 123, 23);
		writeCsvBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wrap.writeCsv();
			}
		});
		writeCsvBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(writeCsvBtn);
		
		writeKmlBtn = new JButton("Write to kml");
		writeKmlBtn.setBounds(10, 41, 123, 23);
		writeKmlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					wrap.writeKml();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		writeKmlBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(writeKmlBtn);
		
		comboPath = new JTextField();
		comboPath.setBounds(289, 42, 337, 20);
		comboPath.setText("Enter a combo csv path");
		frame.getContentPane().add(comboPath);
		comboPath.setColumns(10);
		
		JButton btnEraseDB = new JButton("Erase Database");
		btnEraseDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wrap.getDataBase().clear();
				String rec = Integer.toString(wrap.getDataBase().size());
				recTextField.setText(rec);
				routerTextField.setText(Integer.toString(0));
			}
		});
		btnEraseDB.setBounds(143, 41, 123, 23);
		btnEraseDB.setForeground(Color.RED);
		btnEraseDB.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(btnEraseDB);
		
		JButton btnAddToDb = new JButton("Add to database");
		btnAddToDb.setBounds(143, 10, 123, 23);
		btnAddToDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String wigPath = wigglePath.getText();
				String comPath = comboPath.getText();
				wrap.addToDB(wigPath,comPath);
				String rec = Integer.toString(wrap.getDataBase().size());
				recTextField.setText(rec);
				Set<Line_46> db = wrap.getDataBase();
				int amount = 0;
				for(Line_46 temp : db) {
					amount = amount + temp.getWifiAmount();
				}
				String macs = Integer.toString(amount);
				routerTextField.setText(macs);
			}
		});
		btnAddToDb.setFont(new Font("Tahoma", Font.BOLD, 10));
		frame.getContentPane().add(btnAddToDb);
		
		JLabel recordsLabel = new JLabel("Records:");
		recordsLabel.setBounds(289, 96, 96, 14);
		frame.getContentPane().add(recordsLabel);
		
		JLabel routersLabel = new JLabel("Routers:");
		routersLabel.setBounds(289, 121, 96, 14);
		frame.getContentPane().add(routersLabel);
		
		JLabel statusLabel = new JLabel("Database status:");
		statusLabel.setBounds(289, 73, 96, 14);
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(statusLabel);
		
		JLabel statFilterLabel = new JLabel("Filter: ");
		statFilterLabel.setBounds(289, 146, 96, 14);
		frame.getContentPane().add(statFilterLabel);
		
		JLabel filterByLabel = new JLabel("Filter by:");
		filterByLabel.setBounds(10, 75, 56, 14);
		filterByLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(filterByLabel);
		
		
		JButton btnTime = new JButton("Time");
		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnTime.setBounds(10, 101, 89, 23);
		btnTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(btnTime);
		
		JButton btnDevice = new JButton("Device");
		btnDevice.setBounds(10, 178, 89, 23);
		btnDevice.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(btnDevice);
		
		JButton btnLocation = new JButton("Location");
		btnLocation.setBounds(10, 141, 89, 23);
		btnLocation.setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.getContentPane().add(btnLocation);
		
		recTextField = new JTextField();
		recTextField.setBounds(395, 93, 231, 20);
		frame.getContentPane().add(recTextField);
		recTextField.setColumns(10);
		recTextField.setEditable(false);
		
		routerTextField = new JTextField();
		routerTextField.setBounds(395, 118, 231, 20);
		frame.getContentPane().add(routerTextField);
		routerTextField.setColumns(10);
		routerTextField.setEditable(false);
		
		filterTextField = new JTextField();
		filterTextField.setBounds(395, 143, 231, 20);
		frame.getContentPane().add(filterTextField);
		filterTextField.setColumns(10);
		filterTextField.setEditable(false);
		
	}
}
