package Filters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Set;

import Matala_0.Line_46;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;

public class FilterByLocation implements Filter{
	private ArrayList<Line_46> arr = new ArrayList<Line_46>();
	private ArrayList<Line_46> filtered = new ArrayList<Line_46>();
	private Point3D p;
	private double dist_by_meter;

	public FilterByLocation(double lat, double lon,double alt , double dist_by_meter) {
		p = new Point3D(lat, lon,alt);
		this.dist_by_meter = dist_by_meter;
	}
	public void with() {
		final int R = 6371; // Radius of the earth
		for(Line_46 temp : arr) {
			Point3D tempPoint = new Point3D(temp.getLat(), temp.getLon(),temp.getAlt());
			double distance_from_me=distance(p.getX(), temp.getLat(), p.getY(), p.getY(), 0, 0);
			System.out.println("distance from me: "+distance_from_me);
			if(distance_from_me<dist_by_meter&&distance_from_me!=0)
				filtered.add(temp);		
		}
	}
	public void without() {
		final int R = 6371; // Radius of the earth
		for(Line_46 temp : arr) {
			Point2D tempPoint = new Point2D(temp.getLat(), temp.getLon());
			double distance_from_me=distance(p.getX(), temp.getLat(), p.getY(), p.getY(), p.getZ(), temp.getAlt());
			System.out.println("distance from me: "+distance_from_me);
			if(distance_from_me>dist_by_meter&&distance_from_me!=0)
				filtered.add(temp);	
		}
	}
	private double distance(double lat1, double lat2, double lon1, double lon2, double alt1, double alt2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = alt1 - alt2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}
	public ArrayList<Line_46> getArr() {
		return arr;
	}
	public void setArr(Set<Line_46> dataBase) {
		this.arr.addAll(dataBase);
	}
	public ArrayList<Line_46> getFiltered() {
		return filtered;
	}
	public void setFiltered(ArrayList<Line_46> filtered) {
		this.filtered = filtered;
	}
	@Override
	public String toString() {
		return "Position(radius<"+this.dist_by_meter +", center =("+p.getX()+","+p.getY()+")";
	}

}
