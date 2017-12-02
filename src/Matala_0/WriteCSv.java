package Matala_0;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Matala_0.Line_46;
/**
 * This class implements the Readable interface has two methods:
 * First it reads WiggleWifi files and then it writes a new csv file.
 * The new csv file has a different format and contains only certain data from the original WiggleWifi file.  
 * @author Uriel and Levi
 *
 */
public class WriteCSv implements Readable {
	ArrayList<Line_46> sofi=new ArrayList();

	/**
	 * Opens folder and reads only the csv files.
	 * @param File folder path.
	 */
	public void openFolder(File folder) {

		File[] listOfFiles = folder.listFiles();	
		try {
			FileWriter outfile;
			outfile = new java.io.FileWriter("test_Revital3.csv", true);
			String title=("Time, ID, Lat, Lon, Alt, #number of networks, SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2,SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4,SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6,SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8,SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10");
			outfile.write(title+"\n");
			outfile.close();
		} catch (IOException e) {
			System.out.println("Error in writing the title!");
			e.printStackTrace();
		}

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			System.out.println("file num "+i+" name: "+file.getName());
			if (file.isFile() && file.getName().endsWith(".csv")) {
				read(file.getPath());

			}
		}
		write();
	}
	/**
	 * Reads csv file and prepares it to be written in new format.
	 * @param String file name.
	 */
	public void read(String path) {

		ArrayList<Wifi> arr=new ArrayList();
		String good_id="";
		try{
			Scanner scanner=new Scanner(new FileReader(path));
			String line;
			String lineid=scanner.nextLine();
			String []idFromLine=lineid.split(",");
			String id = idFromLine[5];
			good_id=id.substring(8, id.length());

			scanner.nextLine();
			while(scanner.hasNextLine()){
				line=scanner.nextLine(); 						//get the line

				String []results=line.split(",");				//split the line
				Wifi wifispot= new Wifi();
				wifispot.setMAC(results[0]);
				wifispot.setSSID(results[1]);
				wifispot.setSignal(results[5]);
				wifispot.setFrequency(results[4]);
				wifispot.setTime(results[3]);
				wifispot.setLat(results[6]);
				wifispot.setLon(results[7]);
				wifispot.setAlt(results[8]);
				wifispot.setType(results[10]);
				wifispot.setId(good_id);
				if(wifispot.getType().equals("WIFI"))
					arr.add(wifispot);
			}

			scanner.close();
		}catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}

		/*print the arraylist
	  int i=0;
		for (Wifi temp : arr) {
			temp.toCsv();
			i++;
		}
		System.out.println("size= "+i);
		 */
		//arr=sort(arr);

		int count=0;
		int start=0;
		for (int i = 0; i < arr.size(); i++) {
			Line_46 line=new Line_46();
			if( i+1<arr.size()) {
				String thisrowlat = arr.get(i).getLat();
				String nextrowlat = arr.get(i+1).getLat();
				//System.out.println(thisrowlat.equals(nextrowlat));
				if(!arr.get(i).getTime().substring(0,16).equals(arr.get(i+1).getTime().substring(0,16))	|| !thisrowlat.equals(nextrowlat) ) {

					line.setAlt(Double.parseDouble((arr.get(i).getAlt())));
					line.setLat(Double.parseDouble((arr.get(i).getLat())));
					line.setLon(Double.parseDouble((arr.get(i).getLon())));
					line.setId((arr.get(i).getId()));
					line.setTime((arr.get(i).getTime()));

					int max = -1000;
					int maxRowIndex = 0;
					for (int j =start; j <=i && j<start+10; j++) {
						for(int k=start; k<=i && k<start+10; k++) {
							int curSignal = Integer.parseInt(arr.get(k).getSignal());
							if(curSignal>max ) {
								max=curSignal;
								maxRowIndex = k;
							}
						}
						Wifi4 row= new Wifi4(arr.get(maxRowIndex));
						line.setListOfWifi(row);
						arr.get(maxRowIndex).setSignal("-2000");
						count++;
						max=-1000;
					}
					line.setWifiAmount(count);
					count=0;
					start=i+1;
					sofi.add(line);

				}

			}

			//System.out.println(toCsv(sofi));
		}
		//System.out.println("size of file: "+sofi.size());
	}

	/**
	 * Strings the ArrayList of type Line_46 to csv format.
	 * @param sofi
	 * @return String
	 */
	public String toCsv (ArrayList<Line_46> sofi) {
		String answer="";
		for (int i = 0; i < sofi.size(); i++) {
			answer = answer + sofi.get(i).toCsv();
		}		
		return answer;
	}

	public void write() {
		// TODO Auto-generated method stub

		try {
			FileWriter outfile;
			outfile = new java.io.FileWriter("test_Revital3.csv", true);
			outfile.write(toCsv(sofi));
			outfile.close();

		} catch (IOException e) {
			System.out.println("Error in write() method!");
			e.printStackTrace();
		} 

	}


}
