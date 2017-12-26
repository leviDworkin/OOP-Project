package matala_2;
import java.io.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import Matala_0.Line_46;
import Matala_0.Readable;
import Matala_0.Wifi4;
import Matala_0.lineData;

public class Algo_2 {
	String combo_path;
	String arrNoGps_path;
	ArrayList<Line_46> arrNoGps = new ArrayList<Line_46>();
	ArrayList<Line_46> combo = new ArrayList<Line_46>();

	myHash mh = new myHash();

	public Algo_2(String combo_path,String arrNoGps_path ) {
		this.combo_path=combo_path;
		this.arrNoGps_path=arrNoGps_path;
	}

	public void read() {	
		ArrayList<String[]> arr=new ArrayList<String[]>();
		try{
			Scanner scanner=new Scanner(new FileReader(this.combo_path));
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
			Line_46 line46=new Line_46();
			String temp[]=arr.get(i);
			line46.setLat(Double.parseDouble(temp[2]));
			line46.setLon(Double.parseDouble(temp[3]));
			line46.setAlt(Double.parseDouble(temp[4]));
			line46.setWifiAmount(Integer.parseInt(temp[5]));


			for(int j=7 , k=9; j<temp.length && temp[j]!=null ; j=j+4,k=k+4) {
				Wifi4 wifi=new Wifi4();
				wifi.setMAC(temp[j]);
				wifi.setSignal(temp[k]);
				line46.setListOfWifi(wifi);

			}
			combo.add(line46);
		}

		ArrayList<String[]> arr2=new ArrayList<String[]>();
		try{
			Scanner scanner=new Scanner(new FileReader(this.arrNoGps_path));
			String line;
			while(scanner.hasNextLine()){
				line=scanner.nextLine(); //get the line
				String []results=line.split(",");  //split the line
				if(results[0]!=null)
					arr2.add(results);
			}
			scanner.close();
		}catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}

		for (int i=0; i<arr2.size();i++) {  	//Runs the length of the ArrayList 
			Line_46 line46=new Line_46();
			String temp[]=arr2.get(i);
			line46.setTime(temp[0]);
			line46.setId(temp[1]);
			line46.setWifiAmount(Integer.parseInt(temp[5]));
			//			line46.setLat(Double.parseDouble(temp[2]));
			//			line46.setLon(Double.parseDouble(temp[3]));
			//			line46.setAlt(Double.parseDouble(temp[4]));
			for(int  j=7 , k=9 , s=6 , f=8; j<temp.length && temp[j]!=null ; j=j+4,k=k+4,s=s+4,f=f+4) {
				Wifi4 wifi=new Wifi4();
				wifi.setFrequency(temp[f]);
				wifi.setSSID(temp[s]);
				wifi.setMAC(temp[j]);
				wifi.setSignal(temp[k]);
				line46.setListOfWifi(wifi);
			}
			arrNoGps.add(line46);
		}

		for (int i = 0; i < combo.size(); i++) {
			goToHash(combo.get(i));
		}
		System.out.println(mh.mytoString2());

	}	
	private void goToHash(Line_46 line46) {
		ArrayList<Wifi4>arr=new ArrayList<Wifi4>();
		arr=line46.getListOfWifi();
		for (int i = 0; i < arr.size(); i++) {
			mh.add2(arr.get(i).getMAC(), line46);
		}
	}

	public void calculate() {
		for (Line_46 currLine : arrNoGps) { //Runs the length of arrNoGps
			Set<Line_46> hs = new HashSet<Line_46>();
			for(int i=0;i<currLine.getWifiAmount();i++) {//Asking about each mac one by one
				String currNoGpsMac = currLine.getListOfWifi().get(i).getMAC();
				System.out.println("for this mac: "+currNoGpsMac);
				if(mh.hm46.containsKey(currNoGpsMac)) {
					ArrayList<Line_46> hashValue = mh.hm46.get(currNoGpsMac);
					for (int j = 0; j < hashValue.size(); j++) {
						System.out.println(hashValue.get(j).toCsv());
					}
					hs.addAll(hashValue);
				}else {
					System.out.println("i didn't find in the hashmap");
				}
			}
			Set<piAndLine46> setOfPal = new HashSet<piAndLine46>();
			for(Line_46 comline : hs) {
				double pi = 1;
				for(int i=0;i<currLine.getWifiAmount();i++) {
					double noGpsSig = Double.parseDouble(currLine.getListOfWifi().get(i).getSignal());
					int count=0;
					for(int j=0; j<comline.getWifiAmount();j++) {
						double comSig = Double.parseDouble(comline.getListOfWifi().get(j).getSignal());
						if(noGpsSig==comSig) {
							double diff = difference(noGpsSig,comSig);
							double weight = weight(diff,noGpsSig);
							pi = pi*weight;
							count++;
						}
						if(count==0 && j==comline.getWifiAmount()-1) {
							double diff = 100;
							double weight = weight(diff,noGpsSig);
							pi = pi*weight;

						}	
					}
				}
				piAndLine46 pal = new piAndLine46();
				pal.setLine46(comline);
				pal.setPi(pi);
				setOfPal.add(pal);
			}


			ArrayList<piAndLine46> arrOfPal=new ArrayList();
			arrOfPal.addAll(setOfPal);
			arrOfPal.sort(Comparator.comparing(piAndLine46::getPi));
			
			System.out.println("This is arr of pal:\n");
			for (int j = 0; j < arrOfPal.size(); j++) {
				System.out.println(arrOfPal.get(j).myToString());

			}
			

			System.out.println("I finished line");
		}//Don't leave this for until you set coordinates to currLine!!!!!!!
		
	}

	private double weight(double diff, double noGpsSig) {
		double weight = 10000/( Math.pow(diff, 0.4)*Math.pow(noGpsSig, 2)  );
		
		return weight;
	}

	private double difference(double noGpsSig, double comSig) {
		double diff = Math.abs(noGpsSig-comSig);
		if(diff == 0)
			diff = 3;
		return diff;
	}

	public void write() {
		// TODO Auto-generated method stub
	}

	public String mytoString() {
		String sline="This is combo:\n";
		for (int j = 0; j < combo.size(); j++) {
			sline=sline+combo.get(j).toCsv();
		}
		sline=sline+"**************************\n\n\nThis is arrNoGps:\n";
		for (int j = 0; j < arrNoGps.size(); j++) {
			sline=sline+arrNoGps.get(j).toCsv();
		}
		return sline+"\n";
	}
}
