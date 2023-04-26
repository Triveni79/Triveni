package com.payroll.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
@Component
public class DBUtil {
private static Connection connection = null;
	
	public static Connection getConnection() throws SQLException {
		if(connection != null) {
			return connection;
		}
		else {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/payroll?useSSl=false";
			String user = "root";
			String password = "1234";
			
			try {
				Class.forName(driver);
				connection = DriverManager.getConnection(url, user, password);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
		
	}
}
