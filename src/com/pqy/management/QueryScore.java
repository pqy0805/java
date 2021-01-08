package com.pqy.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import com.pqy.dao.DataBase;
public class QueryScore {
	JFrame frame=new JFrame("个人成绩");
	JButton fail=new JButton("未及格");
	JButton cancel=new JButton("返回");
	JButton save=new JButton("保存成绩表");
	JPanel ptable=new JPanel(); 
	JPanel other=new JPanel(); 
	JScrollPane sp;
	JTable table=new JTable(); 
	String ID;
	Vector columnNames=new Vector();
	Vector rowData=new Vector(); 
	int x=1;
	int num;
	void QueryScore() { 
		
		frame.setSize(500,550);
		frame.setLocation(700, 400);
		frame.setLayout(new BorderLayout());
		other.setLayout(new FlowLayout());
		
		other.add(fail);
		other.add(save);
		fail.addActionListener(new FailActionListener());
		cancel.addActionListener(new ExitListener());
		save.addActionListener(new SaveActionListener());
		sp=new JScrollPane(table);
		ptable.add(sp); 
		frame.add(other,BorderLayout.NORTH);
		frame.add(ptable,BorderLayout.CENTER);
		frame.add(new JPanel().add(cancel),BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void display(String id) {  
		ID=id;
		num=1;
		ResultSet rs=null; 
		DataBase db=new DataBase();
		try {
			int row = 0;//获得记录的总数
			Statement stmt=db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select Score.课程号,课程名,成绩,状态 from Score,Course where Score.ID='"+ID+"'and Score.课程号=Course.课程号 and 状态='Y'");
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
			columnNames.add("成绩");
			while(rs.next()) { 
				Vector hang=new Vector(); 
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				rowData.add(hang);
			}	
			DefaultTableModel model=new DefaultTableModel(rowData,columnNames);
			table.setModel(model);
		} 
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	} 
	
	void displayFail(String id) {  
		ID=id;
		num=2;
		ResultSet rs=null; 
		DataBase db=new DataBase();
		try {
			int row = 0;//获得记录的总数
			Statement stmt=db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select Score.课程号,课程名,成绩,状态 from Score,Course where Score.ID='"+ID+"'and Score.课程号=Course.课程号 and 状态='Y' and 成绩<60 and 成绩 is not Null");
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
			columnNames.add("成绩");
			while(rs.next()) { 
				Vector hang=new Vector(); 
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				rowData.add(hang);
			}	
			DefaultTableModel model=new DefaultTableModel(rowData,columnNames);
			table.setModel(model);
		} 
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	} 

	class SaveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new FileIO().add(ID,num);
			JOptionPane.showMessageDialog(null, "保存该成绩表成功！！","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
    class FailActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			String id=ID;
			QueryScore q=new QueryScore();
			q.displayFail(id);
			q.QueryScore();
		}
		
	}
	
	class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new StudentMenu().buildMenu();
		} 
	 }
	/*public static void main(String[] args) {
		new QueryScore().QueryScore("20190001");
	}*/

}
