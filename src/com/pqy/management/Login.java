package com.pqy.management;

import java.sql.*;
import com.pqy.dao.DataBase;
public class Login {
	boolean found=false;
	Connection con=null;
	Statement stmt;
	ResultSet rs;
	DataBase db=new DataBase();
	
	public boolean isManager(String user,String password) {
		con=db.getConnection();
		try { 
			stmt=con.createStatement();
			//在数据库中找到相应的数据
			rs=stmt.executeQuery("select * from Manager where ID='"+user+"' and 登录密码='"+password+"'");
			if(rs.next())//如果找到found赋为true
				found=true;    
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return found;
	}
	
	public boolean isTeacher(String user,String password) {
		con=db.getConnection();
		try {
			stmt=con.createStatement();
			//在数据库中找到相应的数据
			rs=stmt.executeQuery("select * from Teacher where ID='"+user+"'and 登录密码='"+password+"'");
			if(rs.next())
				found=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
	
	public boolean isStudent(String user,String password) {
		con=db.getConnection(); 
		try { 
			stmt=con.createStatement();
			//在数据库中找到相应的数据
			rs=stmt.executeQuery("select * from Student where ID='"+user+"' and 登录密码='"+password+"'");
			if(rs.next())
				found=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
}
