package matala_2;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filename = "C:\\Users\\Levi\\Desktop\\Year 2\\OOP\\Assignments\\matala 2\\testing\\_comb_all_BM2_.csv";
		Algo_1 a = new Algo_1();
		a.read(filename);
		a.calculate();
		a.write();
	
	}

}
