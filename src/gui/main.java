package gui;

import DB.serverGui;

public class main {

	public static void main(String[] args) {
		GUI window = new GUI();
		window.frame.setVisible(true);
		serverGui sg = new serverGui(window.getWrap());
		sg.frame.setVisible(false);
		sg.setGui(window);
		window.setSg(sg);
		
	}

}
