package Matala_0;

import java.util.Comparator;

import matala_2.piAndLine46;

public class myComperator implements Comparator <piAndLine46> {

	@Override
	public int compare(piAndLine46 o1, piAndLine46 o2) {
		
		if(o1.getPi()>o2.getPi()){
			return 1;
		}
		if(o1.getPi()<o2.getPi()){
			return -1;
		}
		return 0;
	}

}
