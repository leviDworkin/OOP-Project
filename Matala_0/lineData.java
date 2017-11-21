package Matala_0;



/**
 * This class contains the variables, time, longitute, latidude and name.
 * @author Levi and Uriel
 *
 */
public class lineData {

	private String time;
	private Double lat,lon;
	private String name;
	
	public lineData(){
		time = "";
		lat=0.0;
		lon=0.0;
	}
	public lineData(String time, Double lat, Double lon) {
		super();
		this.time = time;
		this.lat = lat;
		this.lon = lon;
	}

	public lineData(String name, String time, Double lat, Double lon) {
		super();
		this.time = time;
		this.lat = lat;
		this.lon = lon;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

}
