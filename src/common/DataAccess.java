package common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataAccess {
	private static String url = "jdbc:mysql://localhost:3306/bookstorefpt?useUnicode=true&characterEncoding=utf-8";
	private static String userName = "root";
	private static String password = "";
	private static Connection connection;

	public static Connection connect() {
		/*
		 * try { Class.forName("com.mysql.jdbc.Driver"); connection =
		 * DriverManager.getConnection(url, userName, password); } catch
		 * (SQLException e) { e.printStackTrace(); System.out.println(
		 * "connect database error"); } catch (ClassNotFoundException e) {
		 * e.printStackTrace(); System.out.println("connect database error"); }
		 */
		try {
			/*InitialContext ctx = new InitialContext();*/
			/*DataSource ds = (DataSource) ctx.lookup("jdbc/bookstorefpt");*/
			
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)
					  envCtx.lookup("jdbc/bookstorefpt");
			// chuoi string giong voi property name trong context.xml
			return ds.getConnection();
		} catch (NamingException | SQLException ex) {
			return null;
		}
	}
}