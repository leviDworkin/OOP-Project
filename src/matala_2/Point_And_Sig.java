package matala_2;
/**
 * This class creates an object containing 5 variables.
 * Mac of wifi as string.
 * Latitude, longitude and altitude as doubles.
 * Signal of wifi as double.
 * @author Levi and Uriel
 *
 */
public class Point_And_Sig {
	private String mac;
	private double lat;
	private double lon;
	private double alt;
	private double signal;
	

	/**
	 * Empty constructor initializing an object of Point_And_Sig with each varible as 0 or an empty string.
	 */
	public Point_And_Sig() {
		this.mac = "";
		this.lat = 0;
		this.lon = 0;
		this.alt = 0;
		this.signal = 0;
	}

	/**
	 * Constructor allowing user to set values to the object.
	 * @param mac String of a mac from a wifi.
	 * @param lat Double latitude.
	 * @param lon Double longitude.
	 * @param alt Double Altitude.
	 * @param signal Double signal from a wifi.
	 */
	public Point_And_Sig(String mac, double lat, double lon, double alt, double signal) {
		super();
		this.mac = mac;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.signal = signal;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getAlt() {
		return alt;
	}
	public void setAlt(double double1) {
		this.alt = double1;
	}
	public double getSignal() {
		return signal;
	}
	public void setSignal(double signal) {
		this.signal = signal;
	}
	/**
	 * 
	 * @return returns a String of this class's object,and it's components, in csv format.
	 */
	public String toCsv() {
        String ans = mac+","+lat+","+lon+","+alt+","+signal+",\n";
		return ans;
	}
	/**
	 * 
	 * @return returns a string of this class's object and it's components.
	 */
	public String mytoString() {
		return "Point_And_Sig [mac=" + mac + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt + ", signal=" + signal+ "]\n ";
	}
	
}
