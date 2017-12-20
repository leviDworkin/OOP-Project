package matala_2;

public class Point_And_Sig {
	private String mac;
	private double lat;
	private double lon;
	private double alt;
	private int signal;
	
	
	public Point_And_Sig(String mac, double lat, double lon, double alt, int signal) {
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
	public int getSignal() {
		return signal;
	}
	public void setSignal(int signal) {
		this.signal = signal;
	}
	
}
