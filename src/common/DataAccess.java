package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccess {
	private static String url = "jdbc:mysql://localhost:3306/bookstorefpt?useUnicode=true&characterEncoding=utf-8";
	private static String userName = "root";
	private static String password = "";
	private static Connection connection;

	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connect database error");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("connect database error");
		}
		return connection;
	}
}
