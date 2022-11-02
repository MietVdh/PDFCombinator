package com.algonquin.pdfcombinator.beans;

import java.util.UUID;

public class User {
	
	private UUID id;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String password;
	
	public User() {
		this.id = UUID.randomUUID();
	}
	
	
	public User(String first, String last, String username, String email, String password) {
		this();
		this.firstName = first;
		this.lastName = last;
		this.userName = username;
		this.email = email;
		this.password = password;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = UUID.fromString(id);
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UUID getID() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
}
