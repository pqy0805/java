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
			//�����ݿ����ҵ���Ӧ������
			rs=stmt.executeQuery("select * from Manager where ID='"+user+"' and ��¼����='"+password+"'");
			if(rs.next())//����ҵ�found��Ϊtrue
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
			//�����ݿ����ҵ���Ӧ������
			rs=stmt.executeQuery("select * from Teacher where ID='"+user+"'and ��¼����='"+password+"'");
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
			//�����ݿ����ҵ���Ӧ������
			rs=stmt.executeQuery("select * from Student where ID='"+user+"' and ��¼����='"+password+"'");
			if(rs.next())
				found=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}
}
