package DB;

import gui.*;
import matala_2.Algo_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import Matala_0.Line_46;

public class ConnectToServer {

	//private ArrayList<Line_46> dataBase = new ArrayList<Line_46>();

	static Set<Line_46> data = new HashSet<Line_46>();
	private static String _ip;
	private static String _port;
	private static String _page;
	private static String _url;
	private static String _user;
	private static String _password;
	private static String _tableName;
	private static Connection _con = null;

	public ConnectToServer() {

	}

	public static int test_ex4_db() {
		Statement st = null;
		ResultSet rs = null;
		int max_id = -1;

		try {     
			_con = DriverManager.getConnection(_url, _user, _password);
			st = _con.createStatement();
			rs = st.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA = '"+_page+"' AND TABLE_NAME = '"+_tableName+"'");
			if (rs.next()) {
				System.out.println("**** Update: "+rs.getString(1));               
			}

			PreparedStatement pst = _con.prepareStatement("SELECT * FROM "+_tableName);
			rs = pst.executeQuery();
			int ind=0;
			Algo_2 a = new Algo_2();
			while (rs.next()) {

				int size = rs.getInt(7);
				int len = 7+2*size;
				if(ind%100==0) {
					String ans = "";
					for(int i=2;i<=len;i++){
						//System.out.print(ind+") "+rs.getString(i)+",");
						ans = ans+rs.getString(i)+",";          			
					}
					a.loadToDB2(ans);
					//System.out.println(ans);
				}
				ind++;
			}
			System.out.println("made it");
			data.addAll(a.getCombo());
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(MySQL_101.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (_con != null) { _con.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(MySQL_101.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return max_id;
	}

	public static String get_ip() {
		return _ip;
	}

	public static void set_ip(String _ip) {
		ConnectToServer._ip = _ip;
	}

	public static String get_port() {
		return _port;
	}

	public static void set_port(String _port) {
		ConnectToServer._port = _port;
	}

	public static String get_page() {
		return _page;
	}

	public static void set_page(String _page) {
		ConnectToServer._page = _page;
	}

	public static String get_url() {
		return _url;
	}

	public static void set_url() {
		ConnectToServer._url = "jdbc:mysql://"+_ip+":"+_port+"/"+_page;
	}

	public static String get_user() {
		return _user;
	}

	public static void set_user(String _user) {
		ConnectToServer._user = _user;
	}

	public static String get_password() {
		return _password;
	}

	public static void set_password(String _password) {
		ConnectToServer._password = _password;
	}

	public static String get_tableName() {
		return _tableName;
	}

	public static void set_tableName(String _tableName) {
		ConnectToServer._tableName = _tableName;
	}

	public static Set<Line_46> getData() {
		return data;
	}

	public static void setData(Set<Line_46> data) {
		ConnectToServer.data = data;
	}
	

}
