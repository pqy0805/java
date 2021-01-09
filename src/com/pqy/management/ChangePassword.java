package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.pqy.dao.DataBase;
public class ChangePassword {
	JFrame f=new JFrame("修改密码");
	JLabel labnew=new JLabel("新密码");
	JLabel labsure=new JLabel("确认密码");
	JTextField txtnew=new JTextField(20);
	JTextField txtsure=new JTextField(20);
	JButton btn1=new JButton("确定");
	JButton btn2=new JButton("取消");
	String User;//用户类型 
	String ID;//ID
	public void ChangeUI(String Id,String user) {
		ID=Id;
		User=user;
		f.setSize(250,240);  
		f.setLocation(700, 450);
		f.setLayout(null);
		f.add(labnew);labnew.setBounds(25, 30, 100, 30);
		f.add(labsure);labsure.setBounds(20, 80, 100, 30);
		f.add(txtnew);txtnew.setBounds(80, 35, 100, 20);
		f.add(txtsure);txtsure.setBounds(80, 85, 100, 20);
		f.add(btn1);btn1.setBounds(45, 120, 60, 30);
		f.add(btn2);btn2.setBounds(135, 120, 60, 30);
	    btn1.addActionListener(new YesActionListener());
	    btn2.addActionListener(new CancelActionListener());
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class CancelActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			if("Manager".equalsIgnoreCase(User)) {
				new ManagerMenu().buildMenu(); 
			}
			else if("Teacher".equalsIgnoreCase(User)){
				new TeacherMenu().buildMenu();
			} 
			else if("Student".equalsIgnoreCase(User)) {
				new StudentMenu().buildMenu();
			}
		}
		
	}
	class YesActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			DataBase db=new DataBase();
			String newword=txtnew.getText();
			String sure=txtsure.getText();
			boolean found=false;
			if(!newword.equals(sure)) {
				JOptionPane.showMessageDialog(null, "两次密码不同！","提示",JOptionPane.INFORMATION_MESSAGE);
			 }
			 else {
				Connection con=db.getConnection();
			   try {
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select 登录密码  from "+User+" where 登录密码='"+newword+"'and ID='"+ID+"'");
				while(rs.next())
					found=true; 
				if(found) {
					JOptionPane.showMessageDialog(null, "新密码不能与旧密码相同！！","提示",JOptionPane.INFORMATION_MESSAGE);
				} 
				else {
					stmt.execute("update "+User+" set 登录密码='"+newword+"' where ID='"+ID+"'");
					JOptionPane.showMessageDialog(null, "修改成功！！","提示",JOptionPane.INFORMATION_MESSAGE);
					
					rs.close(); 
					stmt.close();
					db.closeConnection(con);
					
					f.dispose();
					new ManagerMenu().buildMenu();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		 }
	 				
	  }
		
	}
	
}
