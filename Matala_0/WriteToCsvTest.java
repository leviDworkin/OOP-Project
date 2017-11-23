package Matala_0;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class WriteToCsvTest {

	@Test
	void testMakeMatrix() {
		File file= new File("C:\\Users\\UF\\Desktop\\שנה ב\\מונחה עצמים\\EX_0\\files_to_read\\WigleWifi_20171030122120.csv");
		WriteToCsv a=new WriteToCsv();
		try {
			a.makeMatrix(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			fail("prob with makeMatrix");
		}

	}

	@Test
	void testFindRows() throws IOException {
		File file= new File("C:\\Users\\UF\\Desktop\\שנה ב\\מונחה עצמים\\EX_0\\files_to_read\\WigleWifi_20171030122120.csv");
		WriteToCsv a=new WriteToCsv();
		try {
			a.findRows(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(WriteToCsv.findRows(file)!=610)
			fail("prob with FindRows");
	}

	@Test
	void testFindTheBest() throws IOException, ParseException {
		File file= new File("C:\\Users\\UF\\Desktop\\שנה ב\\מונחה עצמים\\EX_0\\files_to_read\\WigleWifi_20171030122120.csv");
		WriteToCsv a=new WriteToCsv();
		String[][] test=WriteToCsv.findTheBest(a.makeMatrix(file), WriteToCsv.findTime(WriteToCsv.makeMatrix(file)));
		if(test.length!=375)
			fail("Not yet implemented");
	}

	@Test
	void testFindTime() throws IOException {
		File file= new File("C:\\Users\\UF\\Desktop\\שנה ב\\מונחה עצמים\\EX_0\\files_to_read\\WigleWifi_20171030122120.csv");
		WriteToCsv a=new WriteToCsv();
		try {
			a.findTime(a.makeMatrix(file));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(a.equals(null))
			fail("Not yet implemented");
	}

}
