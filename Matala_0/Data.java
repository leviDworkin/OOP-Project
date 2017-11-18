package Matala_0;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * This class prepares data from a csv type file for the WriteKml class to write.
 * @author Levi and Uriel
 *
 */
public class Data {

	ArrayList<String[]> arr;

	public Data(){
		arr= new ArrayList<String[]>();
	}

	/**
	 * Loads all the data from the pathfile.
	 * @param pathFile
	 */
	public void loadAllFromFile(String pathFile){
		try{
			Scanner scanner=new Scanner(new FileReader(pathFile));
			String line;
			lineData record;
			scanner.nextLine();
			while(scanner.hasNextLine()){
				line=scanner.nextLine(); //get the line
				//System.out.println("line"+line);
				String []results=line.split(",");	//split the line
				double lon=Double.parseDouble(results[3]);
				double lat=Double.parseDouble(results[2]);
				String time=results[0];

				record= new lineData(time, lat, lon);


				arr.add(results);

			}
			scanner.close();
		}catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}
		int i=0;
		for (String[] temp : arr) {
			System.out.println(Arrays.toString(temp));
			i++;
		}
		System.out.println(i);
	}
	/**
	 * Receives a longitude and latitude coordinate and loads the data from the rows ,in the csv type file, within a certain radius.  
	 * @param pathFile
	 * @param user_lon 
	 * @param user_lat
	 */
	public void nearFilter(String pathFile, double user_lon, double user_lat){
		double dist=0.001;
		try{
			Scanner scanner=new Scanner(new FileReader(pathFile));

			String line;
			lineData ld;
			scanner.nextLine();
			while(scanner.hasNextLine()){
				line=scanner.nextLine(); //get the line
				//System.out.println("line"+line);
				String []results=line.split(",");	//split the line
				double lon=Double.parseDouble(results[3]);
				double lat=Double.parseDouble(results[2]);
				String time=results[0];
				ld= new lineData(time, lat, lon);

				double check=Math.abs(user_lon-ld.getLon());
				//System.out.println("check= : "+check);
				if(Math.abs(user_lon-ld.getLon())<dist && ld.getLon()!=0)
					arr.add(results);
			}
			scanner.close();
		}catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}
		int i=0;
		for (String[] temp : arr) {
			System.out.println(Arrays.toString(temp));
			i++;

		}
		System.out.println(i);
	}
	/**
	 * Receives a user name and loads the data from the rows,in the csv type file, with that user name. 
	 * @param pathFile
	 * @param user_name
	 */
	public void nameFilter(String pathFile, String user_name){
		try{
			Scanner scanner=new Scanner(new FileReader(pathFile));
			String line;
			lineData ld;
			scanner.nextLine();
			while(scanner.hasNextLine()){
				line=scanner.nextLine(); //get the line
				//System.out.println("line"+line);
				String []results=line.split(",");	//split the line
				double lon=Double.parseDouble(results[3]);
				double lat=Double.parseDouble(results[2]);
				String time=results[0];
				String name=results[1];
				ld= new lineData(name,time, lat, lon);
				//System.out.println("ld getname : "+ld.getName());
				//System.out.println("user_name : "+user_name);
				if(user_name.equals(ld.getName()))
					arr.add(results);
			}
			scanner.close();
		}catch (Exception e){
			System.out.println("Error: "+ e.getMessage());
		}

		int i=0;
		for (String[] temp : arr) {
			System.out.println(Arrays.toString(temp));
			i++;

		}
		System.out.println(i);
	}
}

