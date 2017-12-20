package matala_2;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import Matala_0.Readable;
import Matala_0.lineData;

public class Algo_1 implements Readable {
	ArrayList<String[]> arr;
	HashMap<String,Point_And_Sig> hm=new HashMap<String,Point_And_Sig>(); 
	
	@Override
	public void read(String filename) {	
		try{
			Scanner scanner=new Scanner(new FileReader(filename));
			String line;
			scanner.nextLine();
			while(scanner.hasNextLine()){
				line=scanner.nextLine(); //get the line
				String []results=line.split(",");  //split the line
				arr.add(results);
			}
			scanner.close();
		}catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}
		for (String[] temp : arr) {  //Runs the length of the ArrayList 
			
			for (int i = 0; i < temp.length; i++) { //Runs the length of one line
				double lat = Double.parseDouble(temp[2]);
				double lon = Double.parseDouble(temp[3]);
				double alt = Double.parseDouble(temp[4]);
				int sig = Integer.parseInt(temp[9]);
				for(int j=0; j<temp.length && temp[j]==null ; j=j+4) {
					String mac = temp[j+7];
					Point_And_Sig ps = new Point_And_Sig(mac,lat,lon,alt,sig);
					goToHash(ps);
				}				
			}	
		}
	}
	/**
	 * This method adds the Point_And_Signal as a value to a hashMap, with the mac as its key.
	 * @param ps Point_And_Sig with 5 variables
	 */
	private void goToHash(Point_And_Sig ps) {
		
	}
	
	
	public void calculate() {

	}

	@Override
	public void write() {


	}



}
