package com.pqy.pojo;

public class Manager {
	String ID;
	String password;
	public Manager() {}
	public Manager(String iD, String password) {
		super();
		ID = iD;
		this.password = password;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
