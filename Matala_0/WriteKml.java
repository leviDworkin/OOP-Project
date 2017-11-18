package Matala_0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
/**
 * This class receives csv type files and turns them into kml type files. 
 * @author Levi and Uriel
 *
 */
public class WriteKml {

	/**
	 * Receives a csv type file and creates a kml file from the csv data. 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public static void WriteKml(String filename) throws FileNotFoundException{
		
		Wifi wifi = new Wifi();
		lineData ld;
		Data data = new Data();
		//data.nearFilter(filename, 34.84649015, 32.07683726);
		data.loadAllFromFile(filename);
		Kml kml = new Kml();
		Folder folder = kml.createAndSetFolder();
		
		for (int i = 0; i < data.arr.size(); i++) {
			String[] array = data.arr.get(i);
			ld = new lineData(array[0], Double.parseDouble(array[2]),Double.parseDouble(array[3]));
			//To get all the wifi's on the line
			for (int j = 6; j < array.length; j=j+4) {
				wifi.setSSID(array[j]);
				wifi.setMAC(array[j+1]);
				wifi.setFrequency(Double.parseDouble(array[j+2]));
				wifi.setSignal(Double.parseDouble(array[j+3]));		
				CreatePlacemark(kml, folder, ld.getLat(), ld.getLon(), wifi.getSSID(), wifi.getMAC(), wifi.getSignal(),wifi.getFrequency(),ld.getTime());
				
			}					
		}	
		kml.marshal(new File("finalKml.kml"));
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
	public static void CreatePlacemark(Kml kml, Folder folder, double lat, double lon, String ssid, String mac,double signal,double frequency,String time){
		Placemark placemark = folder.createAndAddPlacemark();
		placemark.withName("SSID: "+ssid).withDescription("\nMAC: "+mac+"\nSignal: "+signal+"\nFrequency: "+frequency+"\nDate: "+time)
		.createAndSetPoint().addToCoordinates(lon, lat);
		
	}
}
