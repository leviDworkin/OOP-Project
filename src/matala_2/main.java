package matala_2;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String filename = "C:\\Users\\Levi\\Desktop\\Year 2\\OOP\\Assignments\\matala 2\\testing\\_comb_all_BM3_.csv";
//		Algo_1 a = new Algo_1();
//		a.setOutputName("weighted_macs.csv");
//		a.read(filename);
//		a.calculate();
//		a.write();
		
	
		Algo_2 b = new Algo_2(filename,"C:\\Users\\Levi\\Desktop\\Year 2\\OOP\\Assignments\\matala 2\\testing\\_comb_no_gps_ts2_.csv");
		b.setOutputName("gps_restored.csv");
		b.read();
		b.calculate();
		b.write();
		
		
	}

}
