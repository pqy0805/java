package com.pqy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	public static Connection getConnection(){
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    Connection con = null;
	    String uri="jdbc:sqlserver://localhost:1433;DatabaseName=stu";
	    String userName="sa";  
	    String password="123456";
	    
	    try {
			Class.forName(driverName);
			con=DriverManager.getConnection(uri,userName,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) { 
			e.printStackTrace(); 
		}finally { 
			return con;
		}
	    
	  }
		public static void closeConnection(Connection con) {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("πÿ±’ ß∞‹£°£°£°");
				}
			} 
		}
		
}



