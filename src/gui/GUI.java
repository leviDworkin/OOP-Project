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
				try {
					wrap.addToDB(wigPath,comPath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		recTextField.setBounds(61, 324, 72, 20);
		frame.getContentPane().add(recTextField);
		recTextField.setColumns(10);
		recTextField.setEditable(false);

		routerTextField = new JTextField();
		routerTextField.setBounds(209, 324, 78, 20);
		frame.getContentPane().add(routerTextField);
		routerTextField.setColumns(10);
		routerTextField.setEditable(false);

		filterTextField = new JTextField();
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
		});
		btnDeleteFilter.setForeground(Color.RED);
		btnDeleteFilter.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDeleteFilter.setBounds(449, 189, 109, 42);
		frame.getContentPane().add(btnDeleteFilter);

		JButton saveFilterBtn = new JButton("Save filter");
		saveFilterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wrap.saveFilter();
			}
		});
		saveFilterBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		saveFilterBtn.setBounds(331, 242, 108, 35);
		frame.getContentPane().add(saveFilterBtn);

		JButton loadFilterBtn = new JButton("Load filter");
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



	}
	public synchronized void myThread() {
		WatchService watchService;
		try {
			watchService = FileSystems.getDefault().newWatchService();
			File folder = new File(this.wigglePath.getText());
			if(folder.isDirectory()) {
				Path path = Paths.get(wigglePath.getText());
				path.register(
						watchService, 
						StandardWatchEventKinds.ENTRY_CREATE, 
						StandardWatchEventKinds.ENTRY_DELETE, 
						StandardWatchEventKinds.ENTRY_MODIFY);

				WatchKey key;
				while ((key = watchService.take()) != null) {

					for (WatchEvent<?> event : key.pollEvents()) {
						System.out.println(
								"Event kind:" + event.kind() 
								+ ". File affected: " + event.context() + ".");
					}
					key.reset();
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
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
