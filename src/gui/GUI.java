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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.sound.sampled.LineListener;
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
import javax.swing.JSeparator;
/**
 * This class translates our OOP project to a GUI representation.
 * @author Levi and Uriel
 *
 */
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
	private JTextField lonFromTextF;
	private JTextField altFromTextF;
	private JLabel labelLat;
	private JLabel labelLon;
	private JLabel labelAlt;
	private JTextField idTextF;
	private JLabel labelID;
	private JRadioButton andRadioBtn;
	private JRadioButton notDevice;
	private JRadioButton orRadioBtn;
	private JRadioButton timeBtn;
	private JRadioButton locationBtn;
	private JRadioButton deviceBtn;
	private JRadioButton notLocation;
	private JRadioButton notTime;
	private JTextField radiusTextField;
	private Random rand = new Random(); 
	private int x = rand.nextInt(2);
	private JTextField algo1TextField;
	private JTextField algo1ResultTextField;
	private JTextField txtEnterStringRep;
	private JLabel algo2result1;
	private JTextField algo2result1textField;
	private JTextField mac2textField;
	private JTextField mac3textField;
	private JTextField mac1textField;
	private JTextField sig1textField;
	private JTextField sig2textField;
	private JTextField sig3textField;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton algo2calc2btn;
	private JLabel lblResult;
	private JTextField algo2result2textField;
	private int in=0;
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */

	public static void main(String[] args) throws InterruptedException, IOException {
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
		//		try {Thread.sleep(100);} catch (Exception e) { System.out.println(e.getMessage());}
		//		myThread();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private synchronized void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1068, 420);
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
				in++;
				String wigPath = wigglePath.getText();
				String comPath = comboPath.getText();
				try {
					wrap.addToDB(wigPath,comPath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				File folder = new File(wigPath);
				if(folder.isDirectory()) {
					MyThread thread = new MyThread();
					thread.setFilePath(wigPath);
					thread.start();
					System.out.println("Thread is running");
				}	
				printStats();
			}
		});
		btnAddToDb.setFont(new Font("Tahoma", Font.BOLD, 10));
		frame.getContentPane().add(btnAddToDb);

		JLabel recordsLabel = new JLabel("Records:");
		recordsLabel.setBounds(10, 327, 56, 14);
		frame.getContentPane().add(recordsLabel);

		JLabel routersLabel = new JLabel("Routers:");
		routersLabel.setBounds(143, 327, 56, 14);
		frame.getContentPane().add(routersLabel);

		JLabel statusLabel = new JLabel("Database status:");
		statusLabel.setBounds(10, 302, 96, 14);
		statusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(statusLabel);

		JLabel statFilterLabel = new JLabel("Filter: ");
		statFilterLabel.setBounds(10, 356, 39, 14);
		frame.getContentPane().add(statFilterLabel);

		JLabel filterByLabel = new JLabel("Filter by:");
		filterByLabel.setBounds(113, 75, 111, 20);
		filterByLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(filterByLabel);

		recTextField = new JTextField();
		recTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		recTextField.setBounds(61, 324, 72, 20);
		frame.getContentPane().add(recTextField);
		recTextField.setColumns(10);
		recTextField.setEditable(false);

		routerTextField = new JTextField();
		routerTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		routerTextField.setBounds(209, 324, 78, 20);
		frame.getContentPane().add(routerTextField);
		routerTextField.setColumns(10);
		routerTextField.setEditable(false);

		filterTextField = new JTextField();
		filterTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		filterTextField.setBounds(43, 353, 583, 20);
		frame.getContentPane().add(filterTextField);
		filterTextField.setColumns(10);
		filterTextField.setEditable(false);

		timeBtn = new JRadioButton("Time");
		timeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(locationBtn.isSelected()&&deviceBtn.isSelected()) {
					timeBtn.setSelected(false);
				}

			}
		});
		timeBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		timeBtn.setBounds(10, 92, 66, 23);
		frame.getContentPane().add(timeBtn);

		locationBtn = new JRadioButton("Location");
		locationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(timeBtn.isSelected()&&deviceBtn.isSelected()) {
					locationBtn.setSelected(false);
				}

			}
		});
		locationBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		locationBtn.setBounds(10, 176, 77, 23);
		frame.getContentPane().add(locationBtn);

		deviceBtn = new JRadioButton("Device");
		deviceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(locationBtn.isSelected()&&timeBtn.isSelected()) {
					deviceBtn.setSelected(false);
				}

			}
		});
		deviceBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		deviceBtn.setBounds(289, 92, 78, 23);
		frame.getContentPane().add(deviceBtn);

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

		lonFromTextF = new JTextField();
		lonFromTextF.setBounds(152, 218, 72, 20);
		frame.getContentPane().add(lonFromTextF);
		lonFromTextF.setColumns(10);

		altFromTextF = new JTextField();
		altFromTextF.setBounds(234, 218, 66, 20);
		frame.getContentPane().add(altFromTextF);
		altFromTextF.setColumns(10);

		JLabel labelFrom = new JLabel("Position");
		labelFrom.setBounds(20, 221, 46, 14);
		frame.getContentPane().add(labelFrom);

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
		andRadioBtn.setBounds(339, 152, 65, 23);
		frame.getContentPane().add(andRadioBtn);

		notDevice = new JRadioButton("Not");
		notDevice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		notDevice.setBounds(373, 92, 66, 23);
		frame.getContentPane().add(notDevice);

		orRadioBtn = new JRadioButton("Or");
		orRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				andRadioBtn.setSelected(false);
			}
		});
		orRadioBtn.setBounds(409, 152, 66, 23);
		frame.getContentPane().add(orRadioBtn);

		JButton btnDeleteFilter = new JButton("Delete filter");
		btnDeleteFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(in>0) {
					try {
						wrap.deleteFilter();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						System.out.println(e1.getMessage());
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					wrap.setStats("");
					printStats();
				}
			}
		});
		btnDeleteFilter.setForeground(Color.RED);
		btnDeleteFilter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteFilter.setBounds(449, 189, 109, 42);
		frame.getContentPane().add(btnDeleteFilter);

		JButton saveFilterBtn = new JButton("Save filter");
		saveFilterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wrap.getFiltered().size()!=0) {		
					try {
						wrap.saveFilter();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}			
			}
		});
		saveFilterBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		saveFilterBtn.setBounds(331, 242, 108, 35);
		frame.getContentPane().add(saveFilterBtn);

		JButton loadFilterBtn = new JButton("Load filter");
		loadFilterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(wrap.getFiltered().size()!=0) {				
					try {
						wrap.loadFilters();
					} catch (ClassNotFoundException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
					printStats();
				}
			}
		});
		loadFilterBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		loadFilterBtn.setBounds(450, 242, 108, 36);
		frame.getContentPane().add(loadFilterBtn);



		notTime = new JRadioButton("Not");
		notTime.setBounds(82, 92, 88, 23);
		frame.getContentPane().add(notTime);

		notLocation = new JRadioButton("Not");
		notLocation.setBounds(99, 176, 56, 23);
		frame.getContentPane().add(notLocation);

		JLabel Radius = new JLabel("Radius in meters:");
		Radius.setBounds(20, 252, 113, 14);
		frame.getContentPane().add(Radius);

		radiusTextField = new JTextField();
		radiusTextField.setBounds(143, 249, 86, 20);
		frame.getContentPane().add(radiusTextField);
		radiusTextField.setColumns(10);

		JButton filterBtn = new JButton("Filter");
		filterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wrap.getFiltered().clear();
				wrap.setStats("");
				if(andRadioBtn.isSelected()) { //And Button
					if(timeBtn.isSelected() && deviceBtn.isSelected()) {
						timeSelected();
						wrap.setStats(wrap.getStats()+" & ");
						deviceSelected();
					}
					if(timeBtn.isSelected() && locationBtn.isSelected()) {
						timeSelected();
						wrap.setStats(wrap.getStats()+" & ");
						locationSelected();
					}
					if(locationBtn.isSelected() && deviceBtn.isSelected()) {
						locationSelected();
						wrap.setStats(wrap.getStats()+" & ");
						deviceSelected();
					}
				}else if(orRadioBtn.isSelected()) { //Or Button
					if(timeBtn.isSelected() && deviceBtn.isSelected()) {
						if(x==1)
							timeSelected();
						else
							deviceSelected();
					}
					if(timeBtn.isSelected() && locationBtn.isSelected()) {
						if(x==1)
							timeSelected();
						else
							locationSelected();
					}
					if(locationBtn.isSelected() && deviceBtn.isSelected()) {
						if(x==1)
							locationSelected();
						else
							deviceSelected();
					}
				}else { //Neither And nor Or
					if(timeBtn.isSelected()) 
						timeSelected();				
					if(deviceBtn.isSelected()) 
						deviceSelected();					
					if(locationBtn.isSelected()) 
						locationSelected();					
				}
				wrap.getDataBase().clear();
				wrap.setDataBase(wrap.getFiltered());
				printStats();
			}

			private void locationSelected() {
				if(notLocation.isSelected())
					wrap.withoutLocation(latFromTextF.getText(),lonFromTextF.getText(),altFromTextF.getText(),radiusTextField.getText());
				else
					wrap.withLocation(latFromTextF.getText(),lonFromTextF.getText(),altFromTextF.getText(),radiusTextField.getText());

			}

			private void deviceSelected() {
				if(notDevice.isSelected())
					wrap.withoutDevice(idTextF.getText());
				else
					wrap.withDevice(idTextF.getText());
			}

			private void timeSelected() {
				if(notTime.isSelected()) {
					try {
						wrap.withoutTime(startTextF.getText(),endTextF.getText());
					} catch (ParseException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
				}else {
					try {
						wrap.withTime(startTextF.getText(),endTextF.getText());
					} catch (ParseException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
				}
			}
		});
		filterBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		filterBtn.setBounds(331, 189, 108, 42);
		frame.getContentPane().add(filterBtn);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBounds(631, 11, 7, 359);
		frame.getContentPane().add(separator);

		JButton algo1Btn = new JButton("Calculate");
		algo1Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mac = algo1TextField.getText(); //d4:68:4d:79:27:a3
				int countMatches = countMatches(mac,':');
				if(countMatches == 5) {
					String ans = wrap.Algo1(mac);
					algo1ResultTextField.setText(ans);
				}else
					algo1ResultTextField.setText("The input must be a proper mac");

			}
		});
		algo1Btn.setFont(new Font("Tahoma", Font.BOLD, 11));
		algo1Btn.setBounds(711, 28, 89, 28);
		frame.getContentPane().add(algo1Btn);

		algo1TextField = new JTextField();
		algo1TextField.setText("Enter mac");
		algo1TextField.setBounds(810, 33, 198, 20);
		frame.getContentPane().add(algo1TextField);
		algo1TextField.setColumns(10);

		algo1ResultTextField = new JTextField();
		algo1ResultTextField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		algo1ResultTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		algo1ResultTextField.setEditable(false);
		algo1ResultTextField.setBounds(648, 64, 394, 20);
		frame.getContentPane().add(algo1ResultTextField);
		algo1ResultTextField.setColumns(10);

		JLabel algo1ResultLabel = new JLabel("Result:");
		algo1ResultLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		algo1ResultLabel.setBounds(648, 43, 56, 19);
		frame.getContentPane().add(algo1ResultLabel);

		JLabel lblNewLabel_2 = new JLabel("Algo 1:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(647, 14, 54, 23);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblAlgo = new JLabel("Algo 2:");
		lblAlgo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAlgo.setBounds(648, 96, 56, 19);
		frame.getContentPane().add(lblAlgo);

		txtEnterStringRep = new JTextField();
		txtEnterStringRep.setText("Enter string rep. of one line in noGps");
		txtEnterStringRep.setBounds(747, 118, 261, 20);
		frame.getContentPane().add(txtEnterStringRep);
		txtEnterStringRep.setColumns(10);

		JButton algo2calc1btn = new JButton("Calculate");
		algo2calc1btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rep = txtEnterStringRep.getText();
				String ans = wrap.algo2rep(rep);
				algo2result1textField.setText(ans);
			}
		});
		algo2calc1btn.setFont(new Font("Tahoma", Font.BOLD, 11));
		algo2calc1btn.setBounds(648, 114, 89, 28);
		frame.getContentPane().add(algo2calc1btn);

		algo2result1 = new JLabel("Result:");
		algo2result1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		algo2result1.setBounds(648, 148, 46, 20);
		frame.getContentPane().add(algo2result1);

		algo2result1textField = new JTextField();
		algo2result1textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		algo2result1textField.setEditable(false);
		algo2result1textField.setBounds(648, 167, 394, 20);
		frame.getContentPane().add(algo2result1textField);
		algo2result1textField.setColumns(10);

		mac2textField = new JTextField();
		mac2textField.setBounds(769, 249, 130, 20);
		frame.getContentPane().add(mac2textField);
		mac2textField.setColumns(10);

		mac3textField = new JTextField();
		mac3textField.setBounds(769, 280, 130, 20);
		frame.getContentPane().add(mac3textField);
		mac3textField.setColumns(10);

		mac1textField = new JTextField();
		mac1textField.setBounds(769, 218, 130, 20);
		frame.getContentPane().add(mac1textField);
		mac1textField.setColumns(10);

		sig1textField = new JTextField();
		sig1textField.setBounds(922, 218, 86, 20);
		frame.getContentPane().add(sig1textField);
		sig1textField.setColumns(10);

		sig2textField = new JTextField();
		sig2textField.setBounds(922, 249, 86, 20);
		frame.getContentPane().add(sig2textField);
		sig2textField.setColumns(10);

		sig3textField = new JTextField();
		sig3textField.setBounds(922, 280, 86, 20);
		frame.getContentPane().add(sig3textField);
		sig3textField.setColumns(10);

		lblNewLabel_3 = new JLabel("Macs:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(768, 194, 56, 20);
		frame.getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Signals:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(922, 194, 56, 20);
		frame.getContentPane().add(lblNewLabel_4);

		algo2calc2btn = new JButton("Calculate");
		algo2calc2btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mac1 = mac1textField.getText();
				String mac2 = mac2textField.getText();
				String mac3 = mac3textField.getText();
				String sig1 = sig1textField.getText();
				String sig2 = sig2textField.getText();
				String sig3 = sig3textField.getText();

				String ans = wrap.sendToAlgo2(mac1,sig1,mac2,sig2,mac3,sig3);
				algo2result2textField.setText(ans);
			}
		});
		algo2calc2btn.setFont(new Font("Tahoma", Font.BOLD, 12));
		algo2calc2btn.setBounds(648, 252, 89, 35);
		frame.getContentPane().add(algo2calc2btn);

		lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResult.setBounds(648, 298, 46, 20);
		frame.getContentPane().add(lblResult);

		algo2result2textField = new JTextField();
		algo2result2textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		algo2result2textField.setEditable(false);
		algo2result2textField.setBounds(648, 324, 394, 20);
		frame.getContentPane().add(algo2result2textField);
		algo2result2textField.setColumns(10);
	}
	/**
	 * counts how many times a char appears in a string.
	 * @param str string
	 * @param c char
	 * @return int the amount of times c is found in str
	 */
	public int countMatches(String str, char c) {
		int count=0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == c)
				count++;
		}
		return count;
	}
	/**
	 * Prints the dataBases current status.
	 */
	public void printStats() {
		String rec = Integer.toString(wrap.getDataBase().size());
		recTextField.setText(rec);
		Set<Line_46> db = wrap.getDataBase();
		int amount = 0;
		for(Line_46 temp : db) {
			amount = amount + temp.getWifiAmount();
		}
		String macs = Integer.toString(amount);
		routerTextField.setText(macs);
		filterTextField.setText(wrap.getStats());
	}
}
