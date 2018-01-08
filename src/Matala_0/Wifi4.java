package Matala_0;

import java.io.Serializable;

/**
 * This class creates an object Wifi4 which contains 4 variables ssid, mac, frequency and signal.
 * @author Uriel and Levi
 *
 */
public class Wifi4 implements Serializable{

	private String SSID;
	private String MAC;
	private String frequency;
	private String signal;

	public String getSSID() {
		return SSID;
	}
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	public String getMAC() {
		return MAC;
	}
	public void setMAC(String mAC) {
		MAC = mAC;
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

	/**
	 * Takes exclusively only 4 variables from the Wifi class and puts them into this classes 4 variables.
	 * @param fullWifi
	 */
	public Wifi4(Wifi fullWifi) {
		this.frequency=fullWifi.getFrequency();
		this.SSID=fullWifi.getSSID();
		this.signal=fullWifi.getSignal();
		this.MAC=fullWifi.getMAC();
	}
	public Wifi4() {
		this.frequency=null;
		this.SSID=null;
		this.signal=null;
		this.MAC=null;
	}
	/**
	 * Strings this class's 4 variables to csv format
	 * @return String
	 */
	public String toCsv() {
		String swifi="";
		swifi= this.SSID+","+this.MAC+","+this.frequency+","+this.signal+",";
		return swifi;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Wifi4) {
			Wifi4 ob = (Wifi4)obj;
			if(!ob.getFrequency().equals(this.frequency) && !ob.getMAC().equals(this.MAC) 
					&& !ob.getSignal().equals(this.signal) && !ob.getSSID().equals(this.SSID))
				return false;
		}		
		return true;
	}
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (null == this.SSID ? 0 : this.SSID.hashCode());
		hash = 31 * hash + (null ==this.frequency ? 0 : this.frequency.hashCode());
		hash = 31 * hash + (null == this.MAC ? 0 : this.MAC.hashCode());
		hash = 31 * hash + (null ==this.signal ? 0 : this.signal.hashCode());
		return hash;
	}
}
