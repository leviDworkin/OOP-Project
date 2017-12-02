package Matala_0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This class is used to store certain data into variables from a WiggleWifi csv file. 
 * @author Uriel and Levi
 *
 */
public class Wifi {

	private String SSID;
	private String MAC;
	private String frequency;
	private String signal;
	private String time;
	private String lat;
	private String lon;
	private String alt;
	private String type;
	private String id;
	
	public String getSSID() {
		return SSID;
	}
	public void setSSID(String ssid) {
		SSID = ssid;
	}
	public String getMAC() {
		return MAC;
	}
	public void setMAC(String mac) {
		MAC = mac;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getSignal() {
		return signal;
	}
	public void setSignal(String signal) {
		this.signal = signal;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}

