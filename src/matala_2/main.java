package matala_2;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String filename = "C:\\Users\\UF\\Desktop\\testing\\testing\\_comb_all_BM3_.csv";
		Algo_1 a = new Algo_1();
		a.read(filename);
		a.calculate();
		a.write("algo1_output.csv");
		
	
//		Algo_2 b = new Algo_2("C:\\Users\\UF\\Desktop\\testing\\testing\\_comb_all_BM3_.csv","C:\\Users\\UF\\Desktop\\testing\\testing\\_comb_no_gps_ts2_.csv");
//		b.read();
//		b.calculate();
//		b.write("withGpstest.csv");

		
	}

}
