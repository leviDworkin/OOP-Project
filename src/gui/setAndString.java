package gui;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import Matala_0.Line_46;
/**
 * This object holds a Set of Line_46's and a string.
 * @author Levi and Uriel
 *
 */
public class setAndString implements Serializable{

	private Set<Line_46> dataBase = new HashSet<Line_46>();
	private String stats;
	public Set<Line_46> getDataBase() {
		return dataBase;
	}
	public void setDataBase(Set<Line_46> dataBase) {
		this.dataBase.addAll(dataBase);
	}
	public String getStats() {
		return stats;
	}
	public void setStats(String stats) {
		this.stats = stats;
	}
	
	
	
	
}
