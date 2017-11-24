package Matala_0;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class DataTest {

	private String pathFile="C:\\Users\\UF\\eclipse-workspace\\EX_0_and_1\\MyTrack_4.csv";
	Data a=new Data(pathFile);
	

	private int num;

	@Test
	void testLoadAllFromFile() {

		try{
			a.loadAllFromFile();
			if(a.getSize()==0) {
				fail("prob with LoadllFromFile");
			}
		}catch (Exception e) {
			fail("prob with LoadAllFromFile");
		}

	}

	@Test
	void testFilterByDist() {

		double user_lat=322.16866695;
		double user_lon=342.81322274;
		double dist_by_meter=100;
		try {
			a.FilterByDist(user_lat, user_lon, dist_by_meter);
			if(a.getSize()!=0)
				fail("prob with FilterByDist");
		} catch (Exception e) { 
			e.printStackTrace();
		}

	}

	@Test
	void testFilterByName() {
		String user_name="blablabla";

		a.filterByName(user_name);
		if(a.getSize()!=0)
			fail("prob with FilterByName");
	}

	@Test//		this test not working as well
	void testFilterGiveAllAfterTime() {
		try {
			String string_date="helloworld";
			SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			Date date_user_want = format.parse(string_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(a.getSize()!=0)
			fail("prob with FilterGiveAllAfterTime");
	}

	@Test
	void testFilterGiveAllBeforeTime() {
		fail("Not yet implemented");
	}

}
