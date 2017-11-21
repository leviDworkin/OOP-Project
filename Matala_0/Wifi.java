package Matala_0;




import java.util.ArrayList;
/**
 * This class contains the variables SSID, MAC, frequency and signal.
 * @author Levi and Uriel
 *
 */
public class Wifi {
	private String SSID;
	private String MAC;
	private double frequency;
	private double signal;
	private ArrayList<Wifi> wifi;
	
	
	public ArrayList<Wifi> getWifi() {
		return wifi;
	}
	public void setWifi(ArrayList<Wifi> wifi) {
		this.wifi = wifi;
	}
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
	public double getFrequency() {
		return frequency;
	}
	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}
	public double getSignal() {
		return signal;
	}
	public void setSignal(double signal) {
		this.signal = signal;
	}

}
