package matala_2;

public class Point_And_Sig {
	private String mac;
	private double lat;
	private double lon;
	private double alt;
	private double signal;
	

	public Point_And_Sig() {
		this.mac = "";
		this.lat = 0;
		this.lon = 0;
		this.alt = 0;
		this.signal = 0;
	}

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
	public String toCsv() {
        String ans = mac+","+lat+","+lon+","+alt+","+signal+",\n";
		return ans;
	}
	public String mytoString() {
		return "Point_And_Sig [mac=" + mac + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt + ", signal=" + signal+ "]\n ";
	}
	
}
