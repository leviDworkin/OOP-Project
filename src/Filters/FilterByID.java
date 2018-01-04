package Filters;
import Matala_0.*;
import java.util.ArrayList;

/**
 * Gets combo and returns an ArrayList<Line_46> with, or without, only the id requested. 
 * @author Levi and Uriel
 *
 */
public class FilterByID {
	private ArrayList<Line_46> arr = new ArrayList<Line_46>();
	private ArrayList<Line_46> filtered = new ArrayList<Line_46>();
	private String id;
	public FilterByID(ArrayList<Line_46> db) {
		this.arr = db;
	}
	
	public void with() {
		for(Line_46 temp : arr) {
			if(temp.getId().equals(this.id))
				filtered.add(temp);		
		}
	}
	public void without() {
		for(Line_46 temp : arr) {
			if(!temp.getId().equals(this.id))
				filtered.add(temp);
		}
		
	}
	
}
