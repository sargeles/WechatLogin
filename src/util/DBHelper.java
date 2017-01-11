package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CRUD of DB.
 * 
 * @author Pactera
 */
public class DBHelper {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/wechatdb";
	private static final String username = "root";
	private static final String password = "123456";
	private static Connection conn = null;

	static {
		try {
			Class.forName(driver);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static Connection getConnection() {

		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn;
		}
		return conn;
	}

	/**
	 * Test demo.
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			Connection conn = DBHelper.getConnection();
			if (conn != null) {
				System.out.println("works right!");
			} else {
				System.out.println("something wrong!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
