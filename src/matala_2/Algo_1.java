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

import Matala_0.Readable;
import Matala_0.lineData;
/**
 * This class reads a csv file, with wifi data in it,
 *  and it calculates the weighted sum of the coordinates of each mac.
 * @author Levi and Uriel
 * This class implements the Readable Interface in package Matala_0
 */
public class Algo_1 implements Readable {
	private String outputName;
	ArrayList<String[]> arr = new ArrayList<String[]>();
	myHash mh=new myHash();
	ArrayList<Point_And_Sig>sofi=new ArrayList<Point_And_Sig>();


	public String getOutputName() {
		return outputName;
	}
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	/**
	 * This method reads the csv file and sends each mac with its coordinates to the HashMap myHash. 
	 */
	@Override
	public void read(String filename) {	
		try{
			Scanner scanner=new Scanner(new FileReader(filename));
			String line;
			while(scanner.hasNextLine()){
				line=scanner.nextLine(); //get the line
				String []results=line.split(",");  //split the line
				if(results[0]!=null)
					arr.add(results);
			}
			scanner.close();
		}catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}

		for (int i=0; i<arr.size()-1;i++) {  	//Runs the length of the ArrayList 
			String temp[]=arr.get(i);
			double lat = Double.parseDouble(temp[2]);
			double lon = Double.parseDouble(temp[3]);
			double alt = Double.parseDouble(temp[4]);

			for(int j=7 , k=9; j<temp.length && temp[j]!=null ; j=j+4,k=k+4) {
				double sig = Double.parseDouble(temp[k]);
				String mac = temp[j];
				Point_And_Sig ps = new Point_And_Sig(mac,lat,lon,alt,sig);
				goToHash(ps);
			}	
		}
	}
	/**
	 * This method adds the Point_And_Signal as a value to a HashMap myHash, with the mac as its key.
	 * @param ps Point_And_Sig with 5 variables
	 */
	private void goToHash(Point_And_Sig ps) {
		mh.add(ps.getMac(), ps);
	}

	/**
	 * Calculates the weight of a signal. f(x) = 1/(x*x).
	 * @param signal from the Point_And_Signal
	 * @return returns the weight of a signal as a double.
	 */
	private double weight(double signal) {
		return 1/(signal*signal);
	}
	/**
	 * calculates the weight of a coordinate. f(x,y) = x*y;
	 * @param coordinate Either a latitude, longitude or altitude coordinate.
	 * @param wsig weight of signal.
	 * @return returns the weight of a coordinate.
	 */
	private double coordinate_weight(double coordinate ,double wsig ) {
		return coordinate*wsig;
	}
	/**
	 * This method runs along the HashMap myHash, and calculates the weighted sum of all the coordinates, 
	 * then giving each mac one new weighted coordinate.
	 */
	public void calculate() {

		for ( String key : mh.hm.keySet() ) {   // runs the length of the hashmap key by key.
			ArrayList<Point_And_Sig> currentArr = new ArrayList<Point_And_Sig>();
			currentArr = mh.hm.get(key);		
			double latSum=0 , lonSum=0, altSum=0, sigSum=0; 
			for(int i=0; i<currentArr.size(); i++) {   //runs the length of the arrayList of point and signals.
				Point_And_Sig ps = currentArr.get(i);
				double wsig = weight(ps.getSignal());
				double wlat = coordinate_weight(ps.getLat(),wsig);
				double wlon = coordinate_weight(ps.getLon(),wsig);
				double walt = coordinate_weight(ps.getAlt(),wsig);
				latSum = latSum+wlat;
				lonSum = lonSum+wlon;
				altSum = altSum+walt;
				sigSum = sigSum+wsig;			
			}
			double firstSignal = currentArr.get(0).getSignal();
			double ansLat = latSum/sigSum;
			double ansLon = lonSum/sigSum;
			double ansAlt = altSum/sigSum;
			Point_And_Sig ans=new Point_And_Sig(key, ansLat, ansLon, ansAlt,firstSignal );
			sofi.add(ans);
		}

	}

	/**
	 * This method writes the results of this class to a new csv file.
	 */

	/**
	 * This method strings an ArrayList<Point_And_Sig> to csv format. 
	 * @param sofi ArrayList of Point and Signals
	 * @return returns a string in csv format.
	 */
	private String toCsv(ArrayList<Point_And_Sig> sofi) {
		String ans = "";
		for (int i = 0; i < sofi.size(); i++) {
			ans = ans+sofi.get(i).toCsv();
		}
		return ans;
	}
	/**
	 * Strings the object of Algo_1 only after the read(String filename) method has already read a file.
	 */
	@Override
	public String toString() {
		return mh.mytoString();
	}
	@Override
	public void write() {
		try {
			FileWriter outfile;
			outfile = new java.io.FileWriter(outputName, true);
			outfile.write(toCsv(sofi));
			outfile.close();

		} catch (IOException e) {
			System.out.println("Error in write() method!");
			e.printStackTrace();
		} 
	}


}
