package Matala_0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import gui.Circle;
/**
 * This class creates an object Line_46 which contains all the data needed to reformat the WiggleWifi csv file.
 * @author Uriel and Levi
 *
 */
public class Line_46 implements Serializable{

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
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Line_46) {
			Line_46 line = (Line_46) obj;
			if( line.getTime().equals(this.Time) && line.getId().equals(this.Id) && line.getAlt()==this.Alt
					&&	line.getLat()==this.Lat && line.getLon()==this.Lon && line.getWifiAmount()==this.WifiAmount ) {
				for(int i=0 ; i<line.getListOfWifi().size();i++) {
					Wifi4 ob = line.getListOfWifi().get(i);
					Wifi4 thi = this.ListOfWifi.get(i);
					if(!ob.equals(thi))
						return false;
				}
			}			
		}		
		return true;
	}
	@Override
	public int hashCode() {
		int hash = 7;
		hash = (int) (31 * hash + this.Lat);
		hash = (int) (31 * hash + this.Lon);
		hash = (int) (31 * hash + this.Alt);
		hash = 31*hash+this.WifiAmount;
		hash = 31 * hash + (null == this.Time ? 0 : this.Time.hashCode());
		hash = 31 * hash + (null ==this.Id ? 0 : this.Id.hashCode());
		for(Wifi4 temp : this.ListOfWifi) {
			hash = 31*hash + temp.hashCode();
		}
		
		return hash;
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
