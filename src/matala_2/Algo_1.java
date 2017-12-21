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

import com.sun.javafx.scene.paint.GradientUtils.Point;

import Matala_0.Readable;
import Matala_0.lineData;

public class Algo_1 implements Readable {

	ArrayList<String[]> arr = new ArrayList<String[]>();
	myHash mh=new myHash();
	ArrayList<Point_And_Sig>sofi=new ArrayList<Point_And_Sig>();

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

		for (int i=0; i<arr.size()-1;i++) {  									//Runs the length of the ArrayList 
			String temp[]=arr.get(i);
			double lat = Double.parseDouble(temp[2]);
			double lon = Double.parseDouble(temp[3]);
			double alt = Double.parseDouble(temp[4]);

			for(int j=7 , k=9; j<temp.length && temp[j]!=null ; j=j+4,k=k+4) {
				double sig = Double.parseDouble(temp[k]);
				String mac = temp[j];
				Point_And_Sig ps = new Point_And_Sig(mac,lat,lon,alt,sig);
				//System.out.println("mac: "+mac);
				goToHash(ps);
			}	
		}
		System.out.println(mh.mytoString());
	}
	/**
	 * This method adds the Point_And_Signal as a value to a hashMap, with the mac as its key.
	 * @param ps Point_And_Sig with 5 variables
	 */
	private void goToHash(Point_And_Sig ps) {
		mh.add(ps.getMac(), ps);
	}

	private double weight(double signal) {
		return 1/(signal*signal);
	}
	private double coordinate_weight(double coordinate ,double wsig ) {
		return coordinate*wsig;
	}
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

	@Override
	public void write() {
		try {
			FileWriter outfile;
			outfile = new java.io.FileWriter("test_1.csv", true);
			outfile.write(toCsv(sofi));
			outfile.close();

		} catch (IOException e) {
			System.out.println("Error in write() method!");
			e.printStackTrace();
		} 
	}

	private String toCsv(ArrayList<Point_And_Sig> sofi) {
		String ans = "";
		for (int i = 0; i < sofi.size(); i++) {
			ans = ans+sofi.get(i).toCsv();
		}
		return ans;
	}
	@Override
	public String toString() {
		return "Algo_1 [mh=" + mh + "]";
	}


}
