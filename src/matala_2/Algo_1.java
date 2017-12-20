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
		

		for (String[] temp : arr) {  //Runs the length of the ArrayList 
			
			for (int i = 0; i < temp.length-1; i++) { //Runs the length of one line
				double lat = Double.parseDouble(temp[2]);
				double lon = Double.parseDouble(temp[3]);
				double alt = Double.parseDouble(temp[4]);
				for(int j=7 , k=9; j+4<temp.length && temp[j]!=null ; j=j+4,k=k+4) {
					double sig = Double.parseDouble(temp[k]);
					String mac = temp[j];
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
		mh.add(ps.getMac(), ps);
		//System.out.println("hash : "+mh.toString());

	}


	public void calculate() {
		
		
		
//		Point_And_Sig ans=new Point_And_Sig(mac, lat, lon, alt, signal);
//		sofi.add(ans);

	}

	@Override
	public void write() {


	}

	@Override
	public String toString() {
		return "Algo_1 [mh=" + mh + "]";
	}


}
