package com.algonquin.pdfcombinator.beans;


public class DBUser extends User {
	
	private String id;
	
	public DBUser() {
		
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

}
