package com.pqy.management;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pqy.dao.*;

class StudentScore{
		String courseNum;
		String Cname;
		String score;
		
		public StudentScore() {
			super();
		}

		public StudentScore(String courseNum, String cname, String score) {
			super();
			this.courseNum = courseNum;
			Cname = cname;
			this.score = score;
		}

		public String getCourseNum() {
			return courseNum;
		}

		public void setCourseNum(String courseNum) {
			this.courseNum = courseNum;
		}

		public String getCname() {
			return Cname;
		}

		public void setCname(String cname) {
			Cname = cname;
		}

		public String getScore() {
			return score;
		}

		public String toString() {
			return "¿Î³ÌºÅ£º" + courseNum + "\t ¿Î³ÌÃû£º" + Cname + "\t³É¼¨:" + score + "\n";
		}

		public void setScore(String score) {
			this.score = score;
		}
		
	}

public class FileIO {
	
	public void add(String ID,int num) {
     DataBase db=new DataBase(); 
	 Connection con=db.getConnection();
	 try {
		Statement stmt=con.createStatement();
		ResultSet rs=null;
		if(num==1) {
		rs=stmt.executeQuery("select Score.¿Î³ÌºÅ,¿Î³ÌÃû,³É¼¨,×´Ì¬ from Score,Course where Score.ID='"+ID+"'and Score.¿Î³ÌºÅ=Course.¿Î³ÌºÅ and ×´Ì¬='Y'");
		}
		else {
			rs=stmt.executeQuery("select Score.¿Î³ÌºÅ,¿Î³ÌÃû,³É¼¨,×´Ì¬ from Score,Course where Score.ID='"+ID+"'and Score.¿Î³ÌºÅ=Course.¿Î³ÌºÅ and ×´Ì¬='Y' and ³É¼¨<60 and ³É¼¨ is not Null");
		}
		List<StudentScore> list=new ArrayList<>();
		while(rs.next()) {
			 
			 StudentScore ss=new StudentScore();
			 ss.setCourseNum(rs.getString(1));
			 ss.setCname(rs.getString(2));
			 ss.setScore(rs.getString(3));
			 list.add(ss);

		 }
	    FileOutputStream fileOutputStream1=new FileOutputStream(new File("D:\\³É¼¨.txt"));
	    
	     byte bt1[]=new byte[1024];
	     bt1= list.toString().getBytes();
	     fileOutputStream1.write(bt1);
	     fileOutputStream1.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	}
	 

}
