package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by CiCi on 2017/2/27.
 */
public class DBHelper {
	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://127.0.0.1:3306/shop?useSSL=true";
	private static final String USER="root";
	private static final String PASSWORD="kongdehui1993";
	static Connection conn = null;
	
	static {
		try {
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//获取链接
	public static Connection getConnection() {
		try {
			if (conn == null) {
				conn = DriverManager.getConnection(URL,USER,PASSWORD);
//				System.out.println("Success" + conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
