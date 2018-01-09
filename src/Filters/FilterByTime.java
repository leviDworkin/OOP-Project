package Filters;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import Matala_0.Line_46;
/**
 * Gets combo and returns an ArrayList<Line_46> with, or without, only the time requested.
 * @author Levi and Uriel
 *
 */
public class FilterByTime implements Filter{

	private ArrayList<Line_46> arr = new ArrayList<Line_46>();
	private ArrayList<Line_46> filtered = new ArrayList<Line_46>();
	private Date startTime , endTime;
	private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String start , end;

	public FilterByTime(String startTime, String endTime) throws ParseException {
		this.startTime=format.parse(startTime);
		this.endTime = format.parse(endTime);
		this.start = startTime;
		this.end = endTime;
	}
	/**
	 * filters out any time not within the given start and end time.
	 */
	public void with()  {
		for(Line_46 temp : arr) {
			try {
				Date tempTime = format.parse(temp.getTime());
				if( (tempTime.after(startTime) || tempTime.equals(startTime)) && tempTime.before(endTime)) {
					filtered.add(temp);
				}
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	/**
	 * filters out any time within the given start and end time.
	 */
	public void without()  {
		for(Line_46 temp : arr) {
			try {
				Date tempTime = format.parse(temp.getTime());			
				if(tempTime.before(startTime) || tempTime.after(endTime)) {				
					filtered.add(temp);	
				}
			} catch (ParseException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
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

	
	public String toStringWith() {
		return start + "<= Data <" + end;
	}
	public String toStringWithout() {
		return start + "> Data >" + end;
	}
}
