package Matala_0;


import java.io.FileReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
/**
 * This class prepares data from a csv type file for the WriteKml class to write.
 * @author Levi and Uriel
 *
 */
public class Data {
	ArrayList<String[]> arr;
	String path;
	private String outputName;
	
	public Data(String path){
		arr= new ArrayList<String[]>();
		this.path=path;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	/**
	 * Loads all the data from the current pathfile.
	 * 
	 */
	public WriteKml loadAllFromFile(){
		String pathFile=this.path;
		WriteKml arbel=new WriteKml();
		arbel.setOutputName(outputName);
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
		System.out.println("I found "+arr.size()+" records");
		arbel.setArr(arr);
		return arbel;
	}
	/**
	 * Receives a longitude and latitude coordinate and requested radius in meters, then checks if the point is near me.  
	 * @param user_lon 
	 * @param user_lat
	 * @param dist_by_meter
	 */
	public WriteKml FilterByDist(double user_lat, double user_lon, double dist_by_meter){
		WriteKml arbel=new WriteKml();
		String pathFile=this.path;
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

				double distance_from_me=distance(user_lat, lat, user_lon, lon, 0, 0);
				System.out.println("distance from me: "+distance_from_me);
				
				if(distance_from_me<dist_by_meter&&distance_from_me!=0) {
					arr.add(results);
					
				}
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
		System.out.println("I found "+arr.size()+" records");
		arbel.setArr(arr);
		return arbel;
	}
	/**
	 * Receives a user name and loads the data from the rows with the same name 
	 * @param pathFile
	 * @param user_name
	 */
	public WriteKml filterByName(String user_name){
		WriteKml arbel=new WriteKml();
		String pathFile=this.path;
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
		System.out.println("I found "+arr.size()+" records");
		arbel.setArr(arr);
		return arbel;
	}
	public WriteKml FilterGiveAllBeforeTime(Date user_date){
		WriteKml arbel=new WriteKml();
		String pathFile=this.path;
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
	
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date wifi_date=format.parse(ld.getTime());
				System.out.println("wifi :"+wifi_date);
				System.out.println("user :"+user_date);
				if(wifi_date.before(user_date))
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
		System.out.println("I found "+arr.size()+" records");
		arbel.setArr(arr);
		return arbel;
	}
	public WriteKml FilterGiveAllAfterTime(Date user_date){
		WriteKml arbel=new WriteKml();
		String pathFile=this.path;
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
	
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date wifi_date=format.parse(ld.getTime());
				System.out.println("wifi :"+wifi_date);
				System.out.println("user :"+user_date);
				if(wifi_date.after(user_date))
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
		System.out.println("I found "+arr.size()+" records");
		arbel.setArr(arr);
		return arbel;
	}
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @param lat1
	 * @param lat2
	 * @param lon1
	 * @param lon2
	 * @param el1
	 * @param el2
	 * @returns Distance in Meters
	 * the function found in the net
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
				* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		double height = el1 - el2;

		distance = Math.pow(distance, 2) + Math.pow(height, 2);

		return Math.sqrt(distance);
	}
	public int getSize() {
		return this.arr.size();
	}

}

