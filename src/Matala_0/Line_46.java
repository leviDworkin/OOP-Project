package Matala_0;

import java.util.ArrayList;
import java.util.List;
/**
 * This class creates an object Line_46 which contains all the data needed to reformat the WiggleWifi csv file.
 * @author Uriel and Levi
 *
 */
public class Line_46 {

	String Time;
	String Id;
	Double Lat;
	Double Lon;
	Double Alt;
	int WifiAmount;
	ArrayList<Wifi4> ListOfWifi=new ArrayList<Wifi4>();
	public String getTime() {
		return Time;
	}
	public void setTime(String time) {
		Time = time;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Double getLat() {
		return Lat;
	}
	public void setLat(Double lat) {
		Lat = lat;
	}
	public Double getLon() {
		return Lon;
	}
	public void setLon(Double lon) {
		Lon = lon;
	}
	public Double getAlt() {
		return Alt;
	}
	public void setAlt(Double alt) {
		Alt = alt;
	}
	public int getWifiAmount() {
		return WifiAmount;
	}
	public void setWifiAmount(int wifiAmount) {
		WifiAmount = wifiAmount;
	}
	public ArrayList<Wifi4> getListOfWifi() {
		return ListOfWifi;
	}
	public void setListOfWifi(Wifi4 wifi) {
		ListOfWifi.add(wifi);
	}
	/**
	 * Strings this classes variables to csv format.
	 * @return String 
	 */
	public String toCsv () {
		String sline="";
		 sline =Time + "," + Id + "," + Lat + "," + Lon+","+Alt+ ","+WifiAmount+",";
		 for (int i = 0; i < this.ListOfWifi.size(); i++) {
			sline = sline + getListOfWifi().get(i).toCsv();
		}
		return sline+"\n";
		}
}
