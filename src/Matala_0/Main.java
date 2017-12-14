package Matala_0;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, ParseException {

		File folder = new File("C:\\Users\\UF\\Desktop\\data");// insert your location
		WriteCSv b=new WriteCSv();
		b.openFolder(folder);
		
		String path="C:\\Users\\Levi\\git\\ex_0\\test_Revital3.csv";
		String string_date ="27-10-2017  16:16:40";
		Data a=new Data(path);

		SimpleDateFormat format= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date_user_want = format.parse(string_date);
		
		//		a.FilterGiveAllBeforeTime(date_user_want);
		//		a.FilterGiveAllAfterTime(date_user_want).WriteKml();
		//      a.loadAllFromFile().WriteKml();
		//		a.FilterByDist(32.1678190337,34.8061381, 5).WriteKml();
		//		a.filterByName("Maxillent").WriteKml();

		System.out.println("hello git");
	}
}
