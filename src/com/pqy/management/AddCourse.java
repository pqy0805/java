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
	JFrame frame=new JFrame("��ӿγ�");
	JLabel labCno=new JLabel("�γ̺�"); 
	JLabel labName=new JLabel("�γ���");
	JLabel labTid=new JLabel("��ʦ��"); 
	
	JTextField txtCno=new JTextField(20);
	JTextField txtName=new JTextField(20); 
	JTextField txtTid=new JTextField(20);
	 
	GridLayout gl=new GridLayout(4,2,12,10);
	JButton add=new JButton("���");
	JButton cancel=new JButton("ȡ��");
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
				ResultSet rs=stmt.executeQuery("select * from Course where �γ̺�='"+Cno+"'");
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "�ÿγ̺��Ѵ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				} 
				else {
				ResultSet rs2=stmt.executeQuery("select Teacher.ID from Course,Teacher where '"+Id+"'=Teacher.ID and Teacher.ID=Course.ID");
				if(rs2.next()) {
					JOptionPane.showMessageDialog(null, "����ʦ�ѽ���һ�ſΣ������ظ���ӣ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				
				else { 
					stmt.execute("insert into Course(�γ̺�,�γ���,ID,״̬,�γ�״̬) values('"+Cno+"','"+Name+"','"+Id+"','N','N')");
					JOptionPane.showMessageDialog(null, "�����ɣ�");
					db.closeConnection(con);
					frame.dispose();
					new TeacherMenu().buildMenu();//���ع���Ա���� 
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
