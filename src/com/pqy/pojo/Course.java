package com.pqy.pojo;

public class Course {
	int courseNum;
	String Cname;
	String TID;
	char state;
	char Cstate;
	public Course() {}
	public Course(int courseNum, String cname, String tID, char state, char cstate) {
		super();
		this.courseNum = courseNum;
		Cname = cname;
		TID = tID;
		this.state = state;
		Cstate = cstate;
	}
	public int getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getTID() {
		return TID;
	}
	public void setTID(String tID) {
		TID = tID;
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	public char getCstate() {
		return Cstate;
	}
	public void setCstate(char cstate) {
		Cstate = cstate;
	}
	

}
