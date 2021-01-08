package com.pqy.dao;

import java.sql.*;

public class DBSelecte {
	public ResultSet SelectStudent(String item,String s) {
		DataBase db=new DataBase(); 
		ResultSet rs = null;
		Connection con=db.getConnection();
		try {
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select ID,姓名,性别,班级号 from Student where "+item+" ='"+s+"'");
		} catch (SQLException e) {
			e.printStackTrace();   
		}finally { 
			return rs;  
		}
	}
	
	public ResultSet SelectTeacher(String item,String s) {
		DataBase db=new DataBase(); 
		ResultSet rs = null; 
		Connection con=db.getConnection();
		try {
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select Teacher.ID,教师名,性别,课程号,课程名 from Teacher,Course where "+item+" ='"+s+"'and Teacher.ID=Course.ID");
		} catch (SQLException e) {
			e.printStackTrace(); 
		}finally {  
			return rs;
		}
		
	}

}
