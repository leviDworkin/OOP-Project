package Matala_0;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class WriteKmlTest {

	@Test
	void testSetPath() {
		String path="blablabla";
		WriteKml a= new WriteKml();
		a.setPath(path);
		if(a.getPath()!=path)
		fail("prob with setPath");
	}

	@Test//			not working well
	void testGetArr() {
		WriteKml a= new WriteKml();
		System.out.println(a.getArr());
		fail("Not yet implemented");
	}

	@Test
	void testWriteKml() {
		String path="C:\\Users\\UF\\eclipse-workspace\\EX_0_and_1\\MyTrack_4.csv";
		Data a=new Data(path);
		try {
			a.loadAllFromFile().WriteKml();
		} catch (Exception e) {
			fail("prob with WriteKml");
		}
	}
}
