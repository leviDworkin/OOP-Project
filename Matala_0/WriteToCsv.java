package Matala_0;



import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;  
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * This class writes a csv type file.
 * @author Levi and Uriel
 *
 */
public class WriteToCsv {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub

		File folder = new File("C:/Users/Levi/Desktop/Year 2/OOP/matala 0/files_to_read");
		File[] listOfFiles = folder.listFiles();
		String [][] arrOfFile = null;
		String [][] temp;
		String ready="";
		String title=("Time, ID, Lat, Lon, Alt, #number of networks, SSID1, MAC1, Frequncy1, Signal1,SSID2, MAC2, Frequncy2, Signal2,SSID3, MAC3, Frequncy3, Signal3,SSID4, MAC4, Frequncy4, Signal4,SSID5, MAC5, Frequncy5, Signal5,SSID6, MAC6, Frequncy6, Signal6,SSID7, MAC7, Frequncy7, Signal7,SSID8, MAC8, Frequncy8, Signal8,SSID9, MAC9, Frequncy9, Signal9,SSID10, MAC10, Frequncy10, Signal10");

		FileWriter outfile = new java.io.FileWriter("example_treks.csv", true); 
		outfile.write(title+"\n");

		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			System.out.println("file num "+i+" name: "+file.getName());
			if (file.isFile() && file.getName().endsWith(".csv")) {

				System.out.println("file on process name: "+file.getName());
				arrOfFile= makeMatrix(file);

				int time[]=findTime(arrOfFile);
				//System.out.println("FindTime:      "+Arrays.toString(time));

				temp=findTheBest(arrOfFile, time);
				System.out.println("i am ready to write");
				ready=writeToReady(temp);

				outfile.write(ready);
				System.out.println("i finished "+file.getName() + " file\n");
			}

		}
		outfile.close();
		System.out.println("\nI finished");
	}

	/**
	 * Turns a file into a matrix of strings 
	 * @param file
	 * @return String[][]
	 * @throws IOException
	 */
	public static String[][] makeMatrix(File file) throws IOException{
		FileReader current = new FileReader(file);
		int rows=findRows(file); 
		String[][]arr= new String[rows][46];
		String[] words;
		BufferedReader reader2=new BufferedReader(current);
		String line2=reader2.readLine();
		for (int i = 0; i < rows; i++) {
			words=line2.split(","); 
			line2=reader2.readLine();
			arr[i]=words;
		}
		return arr;
	}
	/**
	 * Takes a file and counts how many rows there are
	 * @param file
	 * @return int
	 * @throws IOException
	 */
	public static int findRows(File file) throws IOException{
		FileReader current = new FileReader(file);
		BufferedReader reader=new BufferedReader(current);
		int lines = 0;
		while (reader.readLine() != null){
			lines++;
		}
		reader.close();
		return lines;
	}

	/**
	 * Takes a matrix and returns a new matrix containing the 10 strongest signals from each time change
	 * @param mat
	 * @param time
	 * @return String[][]
	 */
	public static String[][] findTheBest(String[][]mat,int[]time){
		String[][]temp = new String[(time.length/2)*10][11];
		int k =0;
		for (int i = 2; i < time.length; i=i+2) {
			if(time[i+1]-time[i]<10){
				for(int j=time[i]; j<time[i+1]; j++){
					temp[k] = mat[j];
					k++;
				}
			}else{
				int max=-1000;
				int index=0;
				for(int h=0; h<10; h++){
					for(int j=time[i]; j<time[i+1]; j++){
						if(Integer.parseInt(mat[j][5]) > max){
							max = Integer.parseInt(mat[j][5]);
							index = j;
						}
					}
					for(int q=0; q<11 ; q++){
						temp[k][q] = mat[index][q];
					}			
					mat[index][5] = "-1000";
					max=-1000;
					k++;
				}
			}		
		}
		int count=0;
		for(int i =0; i<temp.length;i++){
			if(temp[i][3]!=null)
				count++;
		}
		String[][]temp2 = new String[count-2][11];
		for (int i = 0; i < count-2; i++) {
			temp2[i]=temp[i+2];
		}
		temp=temp2;
		return temp;
	}
	/**
	 * Takes a matrix and returns the data in one string formatted as requested  
	 * @param temp
	 * @return String
	 * @throws ParseException
	 */
	public static String writeToReady(String[][]temp) throws ParseException{
		String ready="";
		int[]changes=findTime(temp);
		for (int j = 0; j < changes.length; j=j+2) {			
			String line=temp[changes[j]][3]+","+temp[0][1]+","+temp[changes[j]][6]+","+temp[changes[j]][7]+","+temp[changes[j]][8]+","+(changes[j+1]-changes[j]+1)+",";
			for (int i = changes[j]; i <= changes[j+1]; i++) {
				line=line+temp[i][1]+","+temp[i][0]+","+temp[i][4]+","+temp[i][5]+",";
			}	
			ready=ready+line+"\n";
		}
		return ready;		
	}
	/**
	 * Takes a matrix and finds the rows where a time change occurs and then returns an array with indicating which rows a change occured 
	 * @param String[][]
	 * @return int[]
	 * @throws ParseException
	 */
	public static int[] findTime(String[][] mat) throws ParseException{
		int j=0;
		int k=0;
		Date dt = new Date();
		Date dt2 = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm"); 
		int count=0; 
		for (int i = 2;  i < mat.length-1; i++) {
			dt = df.parse(mat[i][3]);
			dt2 = df.parse(mat[i+1][3]);
			if(dt.compareTo(dt2)!=0 || !mat[i][6].equals(mat[i+1][6]) || !mat[i][7].equals(mat[i+1][7]) || !mat[i][8].equals(mat[i+1][8])){  //date.compare(date2) returns 0 if they are equal		        	
				count++;
			}	        
		}	
		int[] ans = new int[count*2+2];
		for (int i = 2;  i < mat.length-1; i++) {
			dt = df.parse(mat[i][3]);
			dt2 = df.parse(mat[i+1][3]);
			if((dt.compareTo(dt2)!=0) || !mat[i][6].equals(mat[i+1][6]) || !mat[i][7].equals(mat[i+1][7]) || !mat[i][8].equals(mat[i+1][8]) ){		        	
				ans[j] = k;
				ans[j+1]= i;
				j=j+2;	  
				k=i+1;
			}	        
		}	
		ans[j]=k;
		ans[j+1]=mat.length-1;
		return ans;
	}
}
