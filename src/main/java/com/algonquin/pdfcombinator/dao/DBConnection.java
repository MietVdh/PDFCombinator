package com.algonquin.pdfcombinator.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";
	private static final String CONN_STRING = "jdbc:mysql://localhost/dbname";
	
	public static Connection connectToDB(){
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		if(connection!=null) {
			System.out.println("Connected!");
		}
		return connection;
		
	}
}
