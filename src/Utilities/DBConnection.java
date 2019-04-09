package Utilities;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	public static String userName = "sa";
	public static String password = "Sql2017!";
	final static String DB_URL = "jdbc:oracle:MSC_DEPOT_TEST:@ORADB12C.INDDOM10.COM:1521:MSCV5DEV";

	static String SqlQuery = "select * from DSDPT_DEPOTMASTER order by dsdpt_createdon desc";

	static java.sql.Connection Conn = null;
	static Statement stmnt = null;
	static ResultSet result = null;

	public static void main(String[] args) throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Conn = DriverManager.getConnection // (DB_URL, userName, password);
		("jdbc:oracle:thin:@10.200.1.57\\INST1:1521:XE", userName, password);
		if (Conn != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		stmnt = Conn.createStatement();
		result = stmnt.executeQuery(SqlQuery);

		while (result.next()) {
			int myAge = result.getInt(1);
			String myName = result.getString(2);
			System.out.println(myName + "  " + myAge);
		}
		Conn.close();
	}
}
