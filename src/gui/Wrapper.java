package gui;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import Matala_0.*;
import matala_2.*;
/**
 * This class is the bridge between GUI and our code.
 * @author Levi and Uriel
 * 
 */
public class Wrapper {

	private Set<Line_46> dataBase = new HashSet<Line_46>();

	public void writeCsv() {

		WriteCSv b=new WriteCSv();
		b.getSofi().clear();
		b.getSofi().addAll(dataBase);
		b.setOutputName("gui_writeToCSV.csv");
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

	public void addToDB(String wigPath, String comPath) {
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
	public Set getDataBase() {
		return dataBase;
	}

	public void setDataBase(Set dataBase) {
		this.dataBase = dataBase;
	}

}
