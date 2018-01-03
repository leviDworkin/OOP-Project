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
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;

public class GUI {

	private JFrame frame;
	private JTextField wigglePath;
	private Wrapper wrap = new Wrapper();
	private JButton writeKmlBtn;
	private JTextField comboPath;
	private JTextField recTextField;
	private JTextField routerTextField;
	private JTextField filterTextField;
	private JTextField startTextF;
	private JTextField endTextF;
	private JTextField latFromTextF;
	private JTextField latToTextF;
	private JTextField lonFromTextF;
	private JTextField lonToTextF;
	private JTextField altFromTextF;
	private JTextField altToTextF;
	private JLabel labelLat;
	private JLabel labelLon;
	private JLabel labelAlt;
	private JTextField idTextF;
	private JLabel labelID;
	private JRadioButton andRadioBtn;
	private JRadioButton notRadioBtn;
	private JRadioButton orRadioBtn;
	JRadioButton rdbtnTime;
	JRadioButton rdbtnLocation;
	JRadioButton rdbtnDevice;
	
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
		frame.setBounds(100, 100, 660, 420);
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
		recordsLabel.setBounds(10, 327, 56, 14);
		frame.getContentPane().add(recordsLabel);
		
		JLabel routersLabel = new JLabel("Routers:");
		routersLabel.setBounds(125, 327, 56, 14);
		frame.getContentPane().add(routersLabel);
		
		JLabel statusLabel = new JLabel("Database status:");
		statusLabel.setBounds(10, 302, 96, 14);
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(statusLabel);
		
		JLabel statFilterLabel = new JLabel("Filter: ");
		statFilterLabel.setBounds(269, 327, 39, 14);
		frame.getContentPane().add(statFilterLabel);
		
		JLabel filterByLabel = new JLabel("Filter by:");
		filterByLabel.setBounds(113, 75, 111, 20);
		filterByLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(filterByLabel);
		
		recTextField = new JTextField();
		recTextField.setBounds(61, 324, 56, 20);
		frame.getContentPane().add(recTextField);
		recTextField.setColumns(10);
		recTextField.setEditable(false);
		
		routerTextField = new JTextField();
		routerTextField.setBounds(181, 324, 78, 20);
		frame.getContentPane().add(routerTextField);
		routerTextField.setColumns(10);
		routerTextField.setEditable(false);
		
		filterTextField = new JTextField();
		filterTextField.setBounds(307, 324, 280, 20);
		frame.getContentPane().add(filterTextField);
		filterTextField.setColumns(10);
		filterTextField.setEditable(false);
		
		rdbtnTime = new JRadioButton("Time");
		rdbtnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnLocation.isSelected()&&rdbtnDevice.isSelected()) {
					rdbtnTime.setSelected(false);
				}
				
			}
		});
		rdbtnTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnTime.setBounds(10, 92, 78, 23);
		frame.getContentPane().add(rdbtnTime);
		
		rdbtnLocation = new JRadioButton("Location");
		rdbtnLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnTime.isSelected()&&rdbtnDevice.isSelected()) {
					rdbtnLocation.setSelected(false);
				}
				
			}
		});
		rdbtnLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnLocation.setBounds(6, 189, 77, 23);
		frame.getContentPane().add(rdbtnLocation);
		
		rdbtnDevice = new JRadioButton("Device");
		rdbtnDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnLocation.isSelected()&&rdbtnTime.isSelected()) {
					rdbtnDevice.setSelected(false);
				}
				
			}
		});
		rdbtnDevice.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnDevice.setBounds(289, 92, 78, 23);
		frame.getContentPane().add(rdbtnDevice);
		
		startTextF = new JTextField();
		startTextF.setBounds(76, 118, 182, 20);
		frame.getContentPane().add(startTextF);
		startTextF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Start time:");
		lblNewLabel.setBounds(10, 121, 66, 14);
		frame.getContentPane().add(lblNewLabel);
		
		endTextF = new JTextField();
		endTextF.setBounds(76, 149, 182, 20);
		frame.getContentPane().add(endTextF);
		endTextF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("End time:");
		lblNewLabel_1.setBounds(10, 152, 66, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		latFromTextF = new JTextField();
		latFromTextF.setBounds(76, 218, 66, 20);
		frame.getContentPane().add(latFromTextF);
		latFromTextF.setColumns(10);
		
		latToTextF = new JTextField();
		latToTextF.setBounds(76, 255, 66, 20);
		frame.getContentPane().add(latToTextF);
		latToTextF.setColumns(10);
		
		lonFromTextF = new JTextField();
		lonFromTextF.setBounds(152, 218, 72, 20);
		frame.getContentPane().add(lonFromTextF);
		lonFromTextF.setColumns(10);
		
		lonToTextF = new JTextField();
		lonToTextF.setBounds(152, 255, 72, 20);
		frame.getContentPane().add(lonToTextF);
		lonToTextF.setColumns(10);
		
		altFromTextF = new JTextField();
		altFromTextF.setBounds(234, 218, 66, 20);
		frame.getContentPane().add(altFromTextF);
		altFromTextF.setColumns(10);
		
		altToTextF = new JTextField();
		altToTextF.setBounds(234, 255, 66, 20);
		frame.getContentPane().add(altToTextF);
		altToTextF.setColumns(10);
		
		JLabel labelFrom = new JLabel("From:");
		labelFrom.setBounds(20, 221, 46, 14);
		frame.getContentPane().add(labelFrom);
		
		JLabel labelTo = new JLabel("To:");
		labelTo.setBounds(20, 258, 46, 14);
		frame.getContentPane().add(labelTo);
		
		labelLat = new JLabel("Lat");
		labelLat.setHorizontalAlignment(SwingConstants.CENTER);
		labelLat.setBounds(76, 198, 66, 14);
		frame.getContentPane().add(labelLat);
		
		labelLon = new JLabel("Lon");
		labelLon.setHorizontalAlignment(SwingConstants.CENTER);
		labelLon.setBounds(160, 198, 64, 14);
		frame.getContentPane().add(labelLon);
		
		labelAlt = new JLabel("Alt");
		labelAlt.setHorizontalAlignment(SwingConstants.CENTER);
		labelAlt.setBounds(234, 198, 66, 14);
		frame.getContentPane().add(labelAlt);
		
		idTextF = new JTextField();
		idTextF.setBounds(339, 122, 190, 20);
		frame.getContentPane().add(idTextF);
		idTextF.setColumns(10);
		
		labelID = new JLabel("ID:");
		labelID.setBounds(307, 122, 22, 14);
		frame.getContentPane().add(labelID);
		
		andRadioBtn = new JRadioButton("And");
		andRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orRadioBtn.setSelected(false);
			}
		});
		andRadioBtn.setBounds(406, 161, 65, 23);
		frame.getContentPane().add(andRadioBtn);
		
		notRadioBtn = new JRadioButton("Not");
		notRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		notRadioBtn.setBounds(339, 161, 66, 23);
		frame.getContentPane().add(notRadioBtn);
		
		orRadioBtn = new JRadioButton("Or");
		orRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				andRadioBtn.setSelected(false);
			}
		});
		orRadioBtn.setBounds(473, 161, 66, 23);
		frame.getContentPane().add(orRadioBtn);
		
		JButton btnDeleteFilter = new JButton("Delete filter");
		btnDeleteFilter.setForeground(Color.RED);
		btnDeleteFilter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteFilter.setBounds(449, 189, 109, 42);
		frame.getContentPane().add(btnDeleteFilter);
		
		JButton saveFilterBtn = new JButton("Save filter");
		saveFilterBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		saveFilterBtn.setBounds(331, 242, 108, 35);
		frame.getContentPane().add(saveFilterBtn);
		
		JButton loadFilterBtn = new JButton("Load filter");
		loadFilterBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		loadFilterBtn.setBounds(450, 242, 108, 36);
		frame.getContentPane().add(loadFilterBtn);
		
		JButton filterBtn = new JButton("Filter");
		filterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		filterBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		filterBtn.setBounds(331, 189, 108, 42);
		frame.getContentPane().add(filterBtn);
	}
}
