package com.algonquin.pdfcombinator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.algonquin.pdfcombinator.beans.User;

public class ApplicationDao {
	
	private final String COL_USERNAME = "username";
	private final String COL_PASSWORD = "password";
	private final String COL_FIRST_NAME = "first_name";
	private final String COL_LAST_NAME = "last_name";
	private final String COL_EMAIL = "email";
	
	
	public int registerUser(User user) {
		
		int rowsAffected = 0;
		
		try {
			//connect to DB
			Connection connection = DBConnection.connectToDB();
			
			//write insert query to DB
			String insertQuery = "INSERT INTO users VALUES(?,?,?,?,?)";
			
			//set parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(insertQuery);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			
			//execute statement
			rowsAffected = statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}

	public User getUserByUsername(String username) {
		
		User user = null;
		
		// connect to DB
		Connection connection = DBConnection.connectToDB();
		
		// Query DB
		String query = "SELECT * FROM users WHERE username = (?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			
			// retrieve result
			ResultSet result = statement.getResultSet();
			String password = result.getString(COL_PASSWORD);
			String firstName = result.getString(COL_FIRST_NAME);
			String lastName = result.getString(COL_LAST_NAME);
			String email = result.getString(COL_EMAIL);
			
			// Load result into user bean
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// Return user object
		return user;
	}
	
	public boolean validateUser(String username, String password) {
		boolean validated = false;
		User user = null;
		
		// connect to DB
		Connection connection = DBConnection.connectToDB();
		
		// Query DB
		String query = "SELECT * FROM users WHERE username = (?) AND password = (?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2,  password);
			
			// retrieve result
			validated = statement.execute();

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return validated;
	}

}
