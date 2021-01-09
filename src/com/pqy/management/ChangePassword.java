package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.pqy.dao.DataBase;
public class ChangePassword {
	JFrame f=new JFrame("�޸�����");
	JLabel labnew=new JLabel("������");
	JLabel labsure=new JLabel("ȷ������");
	JTextField txtnew=new JTextField(20);
	JTextField txtsure=new JTextField(20);
	JButton btn1=new JButton("ȷ��");
	JButton btn2=new JButton("ȡ��");
	String User;//�û����� 
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
				JOptionPane.showMessageDialog(null, "�������벻ͬ��","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			 }
			 else {
				Connection con=db.getConnection();
			   try {
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select ��¼����  from "+User+" where ��¼����='"+newword+"'and ID='"+ID+"'");
				while(rs.next())
					found=true; 
				if(found) {
					JOptionPane.showMessageDialog(null, "�����벻�����������ͬ����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				} 
				else {
					stmt.execute("update "+User+" set ��¼����='"+newword+"' where ID='"+ID+"'");
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					
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
