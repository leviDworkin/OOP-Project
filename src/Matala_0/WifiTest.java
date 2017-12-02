package Matala_0;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class WifiTest {


	@Test
	void testSetSSID() {
		Wifi a=new Wifi();
		a.setSSID("test");
		if(!a.getSSID().equals("test"))
			fail("prob with setSSID");
	}

	@Test
	void testSetFrequency() {
		Wifi a=new Wifi();
		a.setFrequency("65");
		if(a.getFrequency()!="65")
			fail("prob with SetFrequency");
	}
	@Test
	void testgetMAC() {
		Wifi a=new Wifi();
		a.setMAC("blabla");
		if(!a.getMAC().equals("blabla"))
			fail("prob with SetFrequency");
	}

}
