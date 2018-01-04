package Filters;

import java.util.ArrayList;

import Matala_0.Line_46;

public class FilterByLocation {
	private ArrayList<Line_46> arr = new ArrayList<Line_46>();
	private ArrayList<Line_46> filtered = new ArrayList<Line_46>();
	private double lat , lon, alt ,lat2 , lon2, alt2;
	
	
	public FilterByLocation(double lat, double lon, double alt, double lat2, double lon2, double alt2) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.lat2 = lat2;
		this.lon2 = lon2;
		this.alt2 = alt2;
	}
	public void with() {
		for(Line_46 temp : arr) {
			if(temp.getLat()>lat && temp.getLat()<lat2 && temp.getLon()>lon && temp.getLon()<lon2
					&& temp.getAlt()>alt && temp.getAlt()<alt2)
				filtered.add(temp);		
		}
	}
	public void without() {
		for(Line_46 temp : arr) {
			if(temp.getLat()<lat || temp.getLat()>lat2 && temp.getLon()<lon || temp.getLon()>lon2
					&& temp.getAlt()<alt || temp.getAlt()>alt2)
				filtered.add(temp);	
		}
		
	}
	
}
