package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import com.pqy.dao.DataBase;


public class AddCourse { 
	JFrame frame=new JFrame("添加课程");
	JLabel labCno=new JLabel("课程号"); 
	JLabel labName=new JLabel("课程名");
	JLabel labTid=new JLabel("教师号"); 
	
	JTextField txtCno=new JTextField(20);
	JTextField txtName=new JTextField(20); 
	JTextField txtTid=new JTextField(20);
	 
	GridLayout gl=new GridLayout(4,2,12,10);
	JButton add=new JButton("添加");
	JButton cancel=new JButton("取消");
	JPanel panNorth=new JPanel();
	JPanel panSorth=new JPanel();
	public void add() { 
		frame.setSize(350,300);
		frame.setLocation(700, 450);
		frame.setLayout(new BorderLayout());
		panNorth.setLayout(gl);
		panSorth.setLayout(new FlowLayout());
		panNorth.add(labCno);
		panNorth.add(txtCno);
		panNorth.add(labName);
		panNorth.add(txtName);
		panNorth.add(labTid);
		panNorth.add(txtTid);
		panSorth.add(add);
		panSorth.add(cancel);
		add.addActionListener(new AddActionListener());
		cancel.addActionListener(new CancelActionListener());
		frame.add(panNorth,BorderLayout.NORTH);
		frame.add(panSorth,BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String Cno=txtCno.getText();
			String Name=txtName.getText();  
			String Id=txtTid.getText();
			DataBase db=new DataBase();
			Connection con=db.getConnection();
			try {
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from Course where 课程号='"+Cno+"'");
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "该课程号已存在","提示",JOptionPane.INFORMATION_MESSAGE);
				} 
				else {
				ResultSet rs2=stmt.executeQuery("select Teacher.ID from Course,Teacher where '"+Id+"'=Teacher.ID and Teacher.ID=Course.ID");
				if(rs2.next()) {
					JOptionPane.showMessageDialog(null, "该老师已教授一门课，不可重复添加！！","提示",JOptionPane.INFORMATION_MESSAGE);
				}
				
				else { 
					stmt.execute("insert into Course(课程号,课程名,ID,状态,课程状态) values('"+Cno+"','"+Name+"','"+Id+"','N','N')");
					JOptionPane.showMessageDialog(null, "添加完成！");
					db.closeConnection(con);
					frame.dispose();
					new TeacherMenu().buildMenu();//返回管理员界面 
				}
			}
					
			} catch (SQLException e1) {
				e1.printStackTrace(); 
			} 
			
		}
		
	}
	class CancelActionListener implements ActionListener{
     	public void actionPerformed(ActionEvent e) {
	    frame.dispose();
	    new TeacherMenu().buildMenu();
} 

}
	
}
