package Filters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Matala_0.Line_46;

public class FilterByTime {

	private ArrayList<Line_46> arr = new ArrayList<Line_46>();
	private ArrayList<Line_46> filtered = new ArrayList<Line_46>();
	private Date startTime , endTime;
	
	public FilterByTime(String startTime, String endTime) throws ParseException {
		super();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start=format.parse(startTime);
		Date end = format.parse(endTime);
		this.startTime = start;
		this.endTime = end;
	}
	
	public void with() throws ParseException {
		for(Line_46 temp : arr) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date tempTime = format.parse(temp.getTime());
			if(tempTime.after(startTime)&&tempTime.before(endTime))
				filtered.add(temp);	
		}
	}
	public void without() throws ParseException {
		for(Line_46 temp : arr) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date tempTime = format.parse(temp.getTime());
			if(tempTime.before(startTime) || tempTime.after(endTime))
				filtered.add(temp);	
		}
	}
	
}
