package matala_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class myHash {
	
	HashMap<String,ArrayList<Point_And_Sig>> hm=new HashMap<String , ArrayList<Point_And_Sig>>(); 
	
	public void add(String mac,Point_And_Sig pas) {
		boolean ifcontain = hm.containsKey(mac);
		
		
		if(ifcontain==true) {		//the key is exist
			ArrayList<Point_And_Sig> arr=new ArrayList<Point_And_Sig>();
			arr=hm.get(mac);
			arr.add(pas);
			hm.put(mac, arr);
			
			
			
		}else {
			ArrayList<Point_And_Sig> arr=new ArrayList<Point_And_Sig>();
			arr.add(pas);
			hm.put(mac, arr);

		}
//		Set set=hm.keySet();
//		System.out.println("set:"+set.toString());
	}

	@Override
	public String toString() {
		return hm.toString();
	}
	
}
