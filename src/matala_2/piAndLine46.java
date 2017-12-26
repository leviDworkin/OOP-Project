package matala_2;

import Matala_0.Line_46;

public class piAndLine46 {

	Line_46 line46 = new Line_46();
	double pi;
	
	public Line_46 getLine46() {
		return line46;
	}
	public void setLine46(Line_46 line46) {
		this.line46 = line46;
	}
	public double getPi() {
		return pi;
	}
	public void setPi(double pi) {
		this.pi = pi;
	}
	public String myToString() {
		String s="pi_and_line\n";
		s=s+this.line46.toCsv()+"pi: "+this.getPi();
		return s;
	}
	
}
