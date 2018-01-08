package gui;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Filters.FilterByID;
import Filters.FilterByLocation;
import Filters.FilterByTime;
import Matala_0.*;
import matala_2.*;
/**
 * This class is the bridge between GUI and our code.
 * @author Levi and Uriel
 * 
 */
public class Wrapper implements Serializable{

	private Set<Line_46> dataBase = new HashSet<Line_46>();
	private Set<Line_46> filtered = new HashSet<Line_46>();
	private File f;
	private int num=1 , num2=1;
	private String stats = "";
	
	
	public void writeCsv() {
		
		WriteCSv b=new WriteCSv();
		b.getSofi().clear();
		b.getSofi().addAll(dataBase);
		File save = new File(System.getProperty("user.dir")+"\\"+"gui_writeToCSV.csv");
		if(!save.exists())
			b.setOutputName("gui_writeToCSV.csv");
		else {
			b.setOutputName("gui_writeToCSV("+num+").csv");
			this.num++;
		}
		b.write();
	}

	public void writeKml() throws FileNotFoundException {	
		if(dataBase.size()!=0) {
			WriteKml wk = new WriteKml();
			wk.setDataBase(dataBase);
			wk.setOutputName("gui_writtenToKml.kml");
			wk.writeDbToKml();
		}
	}	

	public void deleteFilter() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream oos = new ObjectInputStream(fis);
		dataBase = (Set<Line_46>) oos.readObject();
		
	}
	public void addToDB(String wigPath, String comPath) throws IOException {
		File folder = new File(wigPath);
		if(folder.isDirectory()) {
			WriteCSv b=new WriteCSv();
			b.openFolder(folder);
			dataBase.addAll(b.getSofi());

		}
		File file = new File(comPath);
		if(file.isFile() && file.getName().endsWith(".csv")) {
			Algo_2 a = new Algo_2();
			a.loadToDB(comPath);
			dataBase.addAll(a.getCombo());
		}
		this.f = new File("currentDatabase.ser");
		FileOutputStream fos = new FileOutputStream(f);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(dataBase);
		
		
	}

	//	public void sendToAlgo1(String path) {
	//		Algo_1 a = new Algo_1();
	//		a.setOutputName("weighted_macs.csv");
	//		a.read(path);
	//		a.calculate();
	//		a.write();
	//	}
	//
	//	public void sendToAlgo2(String path1, String path2) {
	//		Algo_2 b = new Algo_2(path1,path2);
	//		b.setOutputName("gps_restored.csv");
	//		b.read();
	//		b.calculate();
	//		b.write();
	//	}

	public void withoutTime(String startTime, String endTime) throws ParseException {
		FilterByTime fbt = new FilterByTime(startTime, endTime);
		fbt.setArr(dataBase);
		fbt.without();
		filtered.addAll(fbt.getFiltered());	
		stats = stats+ fbt.toStringWithout();
	}
	public void withTime(String startTime, String endTime) throws ParseException {
		FilterByTime fbt = new FilterByTime(startTime, endTime);
		fbt.setArr(dataBase);
		fbt.with();
		filtered.addAll(fbt.getFiltered());	
		stats = stats+ fbt.toStringWith();
	}
	public void withDevice(String id) {
		FilterByID fbid = new FilterByID(id);
		fbid.setArr(dataBase);
		fbid.with();
		filtered.addAll(fbid.getFiltered());
		stats = stats + fbid.toString();
	}

	public void withoutDevice(String id) {
		FilterByID fbid = new FilterByID(id);
		fbid.setArr(dataBase);
		fbid.without();
		filtered.addAll(fbid.getFiltered());	
		stats = stats + fbid.toString();
	}

	public void withoutLocation(String lat,String lon,String alt, String dist_by_meter) {
		FilterByLocation fbl = new FilterByLocation(Double.parseDouble(lat), Double.parseDouble(lon),Double.parseDouble(alt), Double.parseDouble(dist_by_meter));
		fbl.setArr(dataBase);
		fbl.without();
		filtered.addAll(fbl.getFiltered());
		stats = stats + fbl.toString();
	}

	public void withLocation(String lat,String lon,String alt , String dist_by_meter) {
		FilterByLocation fbl = new FilterByLocation(Double.parseDouble(lat), Double.parseDouble(lon), Double.parseDouble(alt), Double.parseDouble(dist_by_meter));
		fbl.setArr(dataBase);
		fbl.with();
		filtered.addAll(fbl.getFiltered());
		stats = stats + fbl.toString();
	}

	public void saveFilter() {
		if(filtered!=null) {
			WriteCSv b=new WriteCSv();
			b.getSofi().clear();
			b.getSofi().addAll(filtered);
			File save = new File(System.getProperty("user.dir")+"\\"+"filtered.csv");
			if(!save.exists())
				b.setOutputName("filtered.csv");
			else {
				b.setOutputName("filtered("+num2+").csv");
				this.num++;
			}
			b.write();
		}		
	}
	public Set getDataBase() {
		return dataBase;
	}

	public void setDataBase(Set dataBase) {
		this.dataBase.addAll(dataBase);
	}
	public Set<Line_46> getFiltered() {
		return filtered;
	}

	public void setFiltered(Set<Line_46> filtered) {
		this.filtered.addAll(filtered);
	}

	public String getStats() {
		return stats;
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

}
