package com.capgemini.connectionclass;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			
			//load
			//DriverManager.registerDriver(new orcale.jdbc.driver.OracleDriver());
			String url= "jdbc:oracle:thin:@localhost:1521:XE";
			
				connection = DriverManager.getConnection(url,"system","root");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;//write a test class to test this connection is created or not.
		
	}
	public static void main(String[] args) {
		System.out.println(DBConnection.getConnection());
	}
	
	
}
