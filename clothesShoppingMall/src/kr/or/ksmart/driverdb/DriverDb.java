package kr.or.ksmart.driverdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverDb {
	
	public static Connection driverDBcon() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		String jdbcDriver = "jdbc:mysql://localhost:3306/dev32db?" +
				"useUnicode=true&characterEncoding=euckr";
		String dbUser = "dev32id";
		String dbPass = "dev32pw";
		conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		return conn;
	}
}
