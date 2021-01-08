package com.pqy.pojo;

public class Student {
	int ID;
	String name;
	String sex;
	String date;
	int classNum;
	String password;
	public Student() {}
	public Student(int iD, String name, String sex, String date, int classNum, String password) {
		super();
		ID = iD;
		this.name = name;
		this.sex = sex;
		this.date = date;
		this.classNum = classNum;
		this.password = password;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
