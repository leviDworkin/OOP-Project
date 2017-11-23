package Matala_0;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class lineDataTest {

	private String time="a a a 00:00:00";
	private Double lat=33.333;
	private Double lon=22.222;
	private String name="blablabla";

	@Test
	void testLineDataStringDoubleDouble() {
		String time="00:00:00 0 0 0000";
		Double lat=33.333;
		Double lon=22.222;
		try {
			lineData a=new lineData(time,lat,lon);
		} catch (Exception e) {
			fail("prob with the constructor");
		}
	}

	@Test
	void testSetName() {
		String name="test";
		lineData a=new lineData(this.time,this.lat,this.lon);
		a.setName(name);
		if(a.getName()!=name)
			fail("prob with set name");
	}

	@Test
	void testSetTime() {
		String time="00000000";
		lineData a=new lineData(this.time,this.lat,this.lon);
		a.setTime(time);
		if(a.getTime()!=time)
			fail("prob with set time");
	}

	@Test
	void testGetLat() {
		lineData a=new lineData(this.time,this.lat,this.lon);

		if(a.getLat()!=this.lat)
			fail("prob with getLat");
	}

	@Test
	void testGetLon() {
		lineData a=new lineData(this.time,this.lat,this.lon);

		if(a.getLon()!=this.lon)
			fail("prob with getLon");
	}

}
