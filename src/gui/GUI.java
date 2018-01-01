package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField wigglePath;
	private Wrapper wrap = new Wrapper();
	private JButton writeKml;
	private JTextField kmlPath;
	private JButton btnAlgo1;
	private JButton btnAlgo2;

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
		frame.setBounds(100, 100, 609, 354);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		wigglePath = new JTextField();
		wigglePath.setText("Enter folder path with wiggle csv's");
		wigglePath.setBounds(10, 11, 255, 20);
		frame.getContentPane().add(wigglePath);
		wigglePath.setColumns(10);
		
		JButton writeCsv = new JButton("WriteCSV");
		writeCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = wigglePath.getText();
				wrap.sendToWriteCsv(path);
			}
		});
		writeCsv.setFont(new Font("Tahoma", Font.BOLD, 12));
		writeCsv.setBounds(39, 36, 136, 23);
		frame.getContentPane().add(writeCsv);
		
		writeKml = new JButton("WriteKML");
		writeKml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = kmlPath.getText();
				try {
					wrap.sendToWriteKml(filename);
				} catch (FileNotFoundException e1) {
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		writeKml.setFont(new Font("Tahoma", Font.BOLD, 12));
		writeKml.setBounds(351, 36, 113, 23);
		frame.getContentPane().add(writeKml);
		
		kmlPath = new JTextField();
		kmlPath.setText("Enter the output from WriteCSV");
		kmlPath.setBounds(288, 11, 262, 20);
		frame.getContentPane().add(kmlPath);
		kmlPath.setColumns(10);
		
		btnAlgo1 = new JButton("Algo 1");
		btnAlgo1.setBounds(71, 162, 89, 23);
		frame.getContentPane().add(btnAlgo1);
		
		btnAlgo2 = new JButton("Algo 2");
		btnAlgo2.setBounds(229, 162, 89, 23);
		frame.getContentPane().add(btnAlgo2);
	}
}
