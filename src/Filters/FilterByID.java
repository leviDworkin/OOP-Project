package Filters;
import Matala_0.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Gets combo and returns an ArrayList<Line_46> with, or without, only the id requested. 
 * @author Levi and Uriel
 *
 */
public class FilterByID implements Filter{
	private ArrayList<Line_46> arr = new ArrayList<Line_46>();
	private ArrayList<Line_46> filtered = new ArrayList<Line_46>();
	private String id;
	
	public FilterByID(String name) {
		this.id = name;
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
		return "ID=" + id;
	}
	
}
