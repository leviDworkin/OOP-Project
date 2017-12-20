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
	}

	public String mytoString() {
		String s="Hash Map keys:";
		int i=1;
		int j=1;
		for ( String key : hm.keySet() ) {
			s=s+"\n"+i+". "+key+" ";
			for (Point_And_Sig v : hm.get(key)) {
			   s=s+"\n\t"+j+". "+v.mytoString();
			   j++;
			}
			j=1;
			i++;
		}
		return s;
	}
	
	
}
