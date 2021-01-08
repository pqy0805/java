package com.pqy.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import com.pqy.dao.DataBase;
public class QueryCourse {
	JFrame frame=new JFrame("选课情况");
	JButton exit=new JButton("退出");
	
	JPanel ptable=new JPanel(); 
	JPanel other=new JPanel(); 
	JScrollPane sp;
	JTable table=new JTable(); 
	String ID;
	Vector columnNames=new Vector();
	Vector rowData=new Vector();
	
	public void QueryCourse(String id) {
		ID=id;
		display();
		frame.setSize(500,550);
		frame.setLocation(700,450);
		frame.setLayout(new BorderLayout());
		
		other.add(exit);
		
		exit.addActionListener(new ExitListener());
		sp=new JScrollPane(table);
		ptable.add(sp); 
		
		frame.add(ptable,BorderLayout.CENTER);
		frame.add(other,BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void display() {  
		ResultSet rs=null; 
		DataBase db=new DataBase();
		try {
			int row = 0;//获得记录的总数
			Statement stmt=db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select Score.课程号,课程名,教师名,Teacher.ID from Score,Course,Teacher where Score.ID='"+ID+"'and Score.课程号=Course.课程号 and 课程状态='Y' and Teacher.ID=Course.ID");
			while(rs.next()) { 
				row++; 
			}
			if(row==0) {
			JOptionPane.showMessageDialog(null, "没有查询结果","提示",JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
		} 
		else { 	
			rs.beforeFirst(); 
			columnNames.add("课程号");
			columnNames.add("课程名"); 
			columnNames.add("授课老师");
			columnNames.add("教职工ID");
			while(rs.next()) { 
				Vector hang=new Vector(); 
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				rowData.add(hang);
			}	
			DefaultTableModel model=new DefaultTableModel(rowData,columnNames);
			table.setModel(model);
		} 
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	} 
	
	
	class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new StudentMenu().buildMenu();
		} 
	 }
	/*public static void main(String [] args) {
		new QueryCourse().QueryCourse("20190001");
	}*/
}
