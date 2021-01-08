package com.pqy.pojo;

public class Score {
	int Sid;
	int courseNum;
	float score; 
	public Score() {}
	public Score(int sid, int courseNum, float score) {
		super();
		Sid = sid;
		this.courseNum = courseNum;
		this.score = score;
	}
	public int getSid() {
		return Sid;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public int getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	

}
