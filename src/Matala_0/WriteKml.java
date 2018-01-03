package Matala_0;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.TimePrimitive;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
/**
 * This class receives csv type files and turns them into kml type files. 
 * @author Levi and Uriel
 *
 */
public class WriteKml  {
	private Set<Line_46> dataBase = new HashSet<Line_46>();
	ArrayList<String[]> arr;
	String path;
	private String outputName;
	
	public Set<Line_46> getDataBase() {
		return dataBase;
	}
	public void setDataBase(Set<Line_46> dataBase) {
		this.dataBase = dataBase;
	}
	public String getOutputName() {
		return outputName;
	}
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	public void writeDbToKml() {
		Kml kml = new Kml();
		Folder folder = kml.createAndSetFolder();
		for (Line_46 temp : dataBase) {
			ArrayList<Wifi4> arr = temp.getListOfWifi();
			//To get all the wifi's on the line
			for (int j = 0; j < arr.size() && arr.get(j)!=null; j=j+4) {
				Wifi4 curr = arr.get(j);
				CreatePlacemark(kml, folder, temp.getLat(), temp.getLon(), curr.getSSID(), curr.getMAC(), curr.getSignal(),curr.getFrequency(),temp.getTime());
			}	
		}
		try {
			kml.marshal(new File(outputName));
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	/**
	 * Creates a kml file from the csv data. 
	 * @throws FileNotFoundException
	 */
	public void WriteKml() throws FileNotFoundException{

		Wifi wifi = new Wifi();
		lineData ld;
		//Data data = new Data(this.path);
	
		Kml kml = new Kml();
		Folder folder = kml.createAndSetFolder();

		for (int i = 0; i < this.arr.size(); i++) {
			String[] array = this.arr.get(i);
			ld = new lineData(array[0], Double.parseDouble(array[2]),Double.parseDouble(array[3]));
			//To get all the wifi's on the line
			for (int j = 6; j < array.length; j=j+4) {
				wifi.setSSID(array[j]);
				wifi.setMAC(array[j+1]);
				wifi.setFrequency(array[j+2]);
				wifi.setSignal(array[j+3]);		
				CreatePlacemark(kml, folder, ld.getLat(), ld.getLon(), wifi.getSSID(), wifi.getMAC(), wifi.getSignal(),wifi.getFrequency(),ld.getTime());
				
			}
			
		}
		System.out.println("size of input: "+this.arr.size());
		
		kml.marshal(new File(outputName));

	}
	/**
	 * Creates a placemark in a folder in a kml file.  
	 * @param kml
	 * @param folder
	 * @param lat
	 * @param lon
	 * @param ssid
	 * @param mac
	 * @param signal
	 * @param frequency
	 * @param time
	 */
	public static void CreatePlacemark(Kml kml, Folder folder, double lat, double lon, String ssid, String mac,String signal,String frequency,String time){
		Placemark placemark = folder.createAndAddPlacemark();
		placemark.withName("SSID: "+ssid).withTimePrimitive(makeTimeStamp(time))
		.withDescription("\nMAC: "+mac+"\nSignal: "+signal+"\nFrequency: "+frequency+"\nDate: "+time)
		.createAndSetPoint().addToCoordinates(lon, lat);
		
	}
	/**
	 * Receives time and makes a TimeStamp.
	 * @param time 
	 * @return TimeStamp
	 */
	private static TimeStamp makeTimeStamp(String time) {
		// TODO Auto-generated method stub
		time = time.replace(' ', 'T');
		time = time+'Z';
		TimeStamp ts = new TimeStamp();
		ts.setWhen(time);

		return ts;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ArrayList<String[]> getArr() {
		return arr;
	}
	public void setArr(ArrayList<String[]> arr) {
		this.arr = arr;
	}

}
