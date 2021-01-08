package com.pqy.pojo;

public class Teacher {
	String ID;
	String name;
	String sex;
	String password;
	public Teacher() {}
	public Teacher(String iD, String name, String sex, String password) {
		super();
		ID = iD;
		this.name = name;
		this.sex = sex;
		this.password = password;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
