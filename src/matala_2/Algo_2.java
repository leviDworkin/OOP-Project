package matala_2;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

import Matala_0.Line_46;
import Matala_0.Readable;
import Matala_0.Wifi4;
import Matala_0.lineData;

public class Algo_2 implements Readable{

	ArrayList<Line_46> arrNoGps = new ArrayList<Line_46>();
	ArrayList<Line_46> combo = new ArrayList<Line_46>();
	myHash mh = new myHash();
	
	@Override
	public void read(String filename) {
		// TODO Auto-generated method stub
		
	}
	
	void calculate() {
		for (Line_46 currLine : arrNoGps) { //Runs the length of arrNoGps
			
			for (int i = 0; i < currLine.getWifiAmount(); i++) { //Runs along the arraylist<wifi4> in line_46
				ArrayList<Line_46> hashValue = mh.hm46.get(currLine.getListOfWifi().get(i).getMAC()); //hashValue of the 
				 
				
			}
			
		}
		
	}
	
	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
