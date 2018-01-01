package gui;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Matala_0.*;
import matala_2.*;
/**
 * This class is the bridge between GUI and our code.
 * @author Levi and Uriel
 * 
 */
public class Wrapper {

	public void sendToWriteCsv(String folderPath) {
		File folder = new File(folderPath);
		WriteCSv b=new WriteCSv();
		b.setOutputName("gui_writeToCSV.csv");
		b.openFolder(folder);
	}

	public void sendToWriteKml(String filename) throws FileNotFoundException {	
		Data a=new Data(filename);
		a.setOutputName("gui_writeToKML.kml");
		a.loadAllFromFile().WriteKml();
		
//		String string_date ="27-10-2017  16:16:40";
//		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//		Date date_user_want = format.parse(string_date);
//				a.FilterGiveAllBeforeTime(date_user_want);
//				a.FilterGiveAllAfterTime(date_user_want).WriteKml();	        
//				a.FilterByDist(32.1678190337,34.8061381, 5).WriteKml();
//				a.filterByName("Maxillent").WriteKml();
	}

	public void sendToAlgo1(String path) {
		Algo_1 a = new Algo_1();
		a.setOutputName("weighted_macs.csv");
		a.read(path);
		a.calculate();
		a.write();
	}

	public void sendToAlgo2(String path1, String path2) {
		Algo_2 b = new Algo_2(path1,path2);
		b.setOutputName("gps_restored.csv");
		b.read();
		b.calculate();
		b.write();
	}
	
}
