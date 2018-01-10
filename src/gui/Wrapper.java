package gui;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Filters.FilterByID;
import Filters.FilterByLocation;
import Filters.FilterByTime;
import Matala_0.*;
import matala_2.*;
/**
 * This class is the bridge between GUI and our code.
 * @author Levi and Uriel
 * 
 */
public class Wrapper implements Serializable{
	private static final Double NumberFormatException = null;
	private setAndString sas = new setAndString();
	private Set<Line_46> dataBase = new HashSet<Line_46>();
	private Set<Line_46> filtered = new HashSet<Line_46>();
	private File f,temp;
	private ArrayList<File> saved = new ArrayList<File>();
	private int num=1 , num2=1;
	private String stats = "";
	private int indexSaved=0 , indexRetrieve=0;
	/**
	 * writes the dataBase to a csv file
	 */
	public void writeCsv() {
		WriteCSv b=new WriteCSv();
		b.getSofi().clear();
		b.getSofi().addAll(dataBase);
		File save = new File(System.getProperty("user.dir")+"\\"+"gui_writeToCSV.csv");
		if(!save.exists())
			b.setOutputName("gui_writeToCSV.csv");
		else {
			b.setOutputName("gui_writeToCSV("+num+").csv");
			this.num++;
		}
		b.write();
	}
	/**
	 * Writes the dataBase to a kml file
	 * @throws FileNotFoundException
	 */
	public void writeKml() throws FileNotFoundException {	
		if(dataBase.size()!=0) {
			WriteKml wk = new WriteKml();
			wk.setDataBase(dataBase);
			wk.setOutputName("gui_writtenToKml.kml");
			wk.writeDbToKml();
		}
	}	
	/**
	 * Removes the filter and restores the dataBase
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void deleteFilter() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream oos = new ObjectInputStream(fis);
		dataBase = (Set<Line_46>) oos.readObject();

	}
	/**
	 * Adds the input files to the dataBase
	 * @param wigPath folder
	 * @param comPath combo file
	 * @throws IOException
	 */
	public void addToDB(String wigPath, String comPath) throws IOException {
		File folder = new File(wigPath);
		if(folder.isDirectory()) {
			WriteCSv b=new WriteCSv();
			b.openFolder(folder);
			dataBase.addAll(b.getSofi());

		}
		File file = new File(comPath);
		if(file.isFile() && file.getName().endsWith(".csv")) {
			Algo_2 a = new Algo_2();
			a.loadToDB(comPath);
			dataBase.addAll(a.getCombo());
		}
		this.f = new File("currentDatabase.ser");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(dataBase);	
	}
	/**
	 * calculates the macs location based on the data in the dataBase
	 * @param mac 
	 * @return core coordinate
	 */
	public String Algo1(String mac) {
		Algo_1 a = new Algo_1();
		a.setDataBase(this.dataBase);
		a.loadToDB();
		a.calculate();
		Point_And_Sig pas = new Point_And_Sig();
		for(Point_And_Sig temp : a.getSofi()) {
			if(temp.getMac().equals(mac)) {
				pas.setLat(temp.getLat());
				pas.setLon(temp.getLon());
				pas.setAlt(temp.getAlt());
			}
		}
		if(pas.getLat()==0 && pas.getLon()==0 && pas.getAlt()==0)
			return "This Mac was not found in the DataBase";
		return "Lat:"+pas.getLat()+" Lon:"+pas.getLon()+" Alt:"+pas.getAlt();
	}
	/**
	 * calculates the macs location based on the data in the dataBase
	 * @param mac1
	 * @param sig1
	 * @param mac2
	 * @param sig2
	 * @param mac3
	 * @param sig3
	 * @return string coordinate
	 */
	public String sendToAlgo2(String mac1,String sig1,String mac2,String sig2,String mac3,String sig3) {
		String ans = "";
		Wifi4 wifi1 = new Wifi4();
		if(mac1=="" || sig1=="")
			wifi1 = null;
		else {wifi1.setMAC(mac1); wifi1.setSignal(sig1);}				
		Wifi4 wifi2 = new Wifi4();
		if(mac2=="" || sig2=="")
			wifi2=null;
		else {wifi2.setMAC(mac2); wifi2.setSignal(sig2);}				
		Wifi4 wifi3 = new Wifi4();
		if(mac3=="" || sig3 == "")
			wifi3=null;
		else {wifi3.setMAC(mac3); wifi3.setSignal(sig3);}			
		Algo_2 b = new Algo_2();
		ArrayList<Line_46> array = new ArrayList<Line_46>();
		array.addAll(dataBase);
		b.setCombo(array);
		b.addToHash();
		array.clear();
		Line_46 line = new Line_46();
		if(wifi1!=null)
			line.getListOfWifi().add(wifi1);
		if(wifi2!=null)
			line.getListOfWifi().add(wifi2);
		if(wifi3!=null)
			line.getListOfWifi().add(wifi3);
		if(wifi1==null && wifi2==null && wifi3==null) {
			ans = "No macs entered";
		}else {
			line.setWifiAmount(line.getListOfWifi().size()-1);
			array.add(line);
			b.setArrNoGps(array);
			b.calcV2();
			if(b.getArrNoGps().get(0).getLat()==null) {
				ans = "mac not found";
			}else {
				double lat = b.getArrNoGps().get(0).getLat();
				double lon = b.getArrNoGps().get(0).getLon();
				double alt = b.getArrNoGps().get(0).getAlt();
				ans = "Lat:"+Double.toString(lat) + " Lon:"+Double.toString(lon) + " Alt:"+Double.toString(alt);
			}			
		}					
		return ans;
	}
	/**
	 *  calculates the missing location based on the data in the dataBase
	 * @param rep
	 * @return string coordinate
	 */
	public String algo2rep(String rep) {
		String ans="Invalid input";
		if(!rep.contains("Enter string rep. of one line in noGps") && isLine46(rep)) {
			Algo_2 a = new Algo_2();
			ArrayList<Line_46> array = new ArrayList<Line_46>();
			array.addAll(dataBase);
			a.setCombo(array);
			a.calcV1(rep);
			a.addToHash();
			a.calcV2();
			if(a.getArrNoGps().get(0).getLat()==null) {
				ans = "macs not found";
			}else {
				double lat = a.getArrNoGps().get(0).getLat();
				double lon = a.getArrNoGps().get(0).getLon();
				double alt = a.getArrNoGps().get(0).getAlt();
				ans = "Lat:"+Double.toString(lat) + " Lon:"+Double.toString(lon) + " Alt:"+Double.toString(alt);
			}		
		}

		return ans;
	}
	/**
	 * counts how many times a char appears in a string.
	 * @param str string
	 * @param c char
	 * @return int the amount of times c is found in str
	 */
	public int countMatches(String str, char c) {
		int count=0;
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == c)
				count++;
		}
		return count;
	}
	/**
	 * checks if a string is of type line_46
	 * @param rep string of combo without gps
	 * @return true if rep is of line_46
	 */
	private boolean isLine46(String rep) {
		boolean ans = true;
		if(countMatches(rep, ',')>=9) {
			
		}else
			ans = false;
		return ans;
	}
	/**
	 * filters the dataBase based on the time input
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	public void withoutTime(String startTime, String endTime) throws ParseException {
		FilterByTime fbt = new FilterByTime(startTime, endTime);
		fbt.setArr(dataBase);
		fbt.without();
		filtered.addAll(fbt.getFiltered());	
		stats = stats+ fbt.toStringWithout();
	}
	/**
	 * filters the dataBase based on the time input
	 * @param startTime
	 * @param endTime
	 * @throws ParseException
	 */
	public void withTime(String startTime, String endTime) throws ParseException {
		FilterByTime fbt = new FilterByTime(startTime, endTime);
		fbt.setArr(dataBase);
		fbt.with();
		filtered.addAll(fbt.getFiltered());	
		stats = stats+ fbt.toStringWith();
	}
	/**
	 * filters the dataBase based on the id input
	 * @param id
	 */
	public void withDevice(String id) {
		FilterByID fbid = new FilterByID(id);
		fbid.setArr(dataBase);
		fbid.with();
		filtered.addAll(fbid.getFiltered());
		stats = stats + fbid.toString();
	}
	/**
	 * filters the dataBase based on the id input
	 * @param id
	 */
	public void withoutDevice(String id) {
		FilterByID fbid = new FilterByID(id);
		fbid.setArr(dataBase);
		fbid.without();
		filtered.addAll(fbid.getFiltered());	
		stats = stats + fbid.toString();
	}
	/**
	 * filters the dataBase based on the location input
	 * @param lat
	 * @param lon
	 * @param alt
	 * @param dist_by_meter
	 */
	public void withoutLocation(String lat,String lon,String alt, String dist_by_meter) {
		FilterByLocation fbl = new FilterByLocation(Double.parseDouble(lat), Double.parseDouble(lon),Double.parseDouble(alt), Double.parseDouble(dist_by_meter));
		fbl.setArr(dataBase);
		fbl.without();
		filtered.addAll(fbl.getFiltered());
		stats = stats + fbl.toString();
	}
	/**
	 *  filters the dataBase based on the location input
	 * @param lat coordinate
	 * @param lon coordinate
	 * @param alt coordinate
	 * @param dist_by_meter radius
	 */
	public void withLocation(String lat,String lon,String alt , String dist_by_meter) {
		FilterByLocation fbl = new FilterByLocation(Double.parseDouble(lat), Double.parseDouble(lon), Double.parseDouble(alt), Double.parseDouble(dist_by_meter));
		fbl.setArr(dataBase);
		fbl.with();
		filtered.addAll(fbl.getFiltered());
		stats = stats + fbl.toString();
	}
	/**
	 * saves the current filter 
	 * @throws IOException 
	 */
	public void saveFilter() throws IOException {
		temp = new File("currentFiltered"+num2+".ser");
		num2++;
		saved.add(temp); 
		indexSaved++;
		sas.setDataBase(filtered);
		sas.setStats(stats);
		FileOutputStream fos = new FileOutputStream(temp);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(sas);	
	}	

	public void loadFilters() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(saved.get(indexRetrieve++));
		ObjectInputStream ois = new ObjectInputStream(fis);
		sas = (setAndString) ois.readObject();
		dataBase = sas.getDataBase();
		this.stats = sas.getStats();
		if(indexRetrieve==saved.size())
			indexRetrieve=0;
	}
	public Set getDataBase() {
		return dataBase;
	}

	public void setDataBase(Set dataBase) {
		this.dataBase.addAll(dataBase);
	}
	public Set<Line_46> getFiltered() {
		return filtered;
	}

	public void setFiltered(Set<Line_46> filtered) {
		this.filtered.addAll(filtered);
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}


}
