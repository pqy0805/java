package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import com.pqy.management.AddCourse.AddActionListener;
import com.pqy.management.AddCourse.CancelActionListener;
import com.pqy.dao.DataBase;
public class AddScore {
	JFrame frame=new JFrame("¼��ɼ�"); 
	JLabel labSno=new JLabel("ѧ��"); 
	JLabel labScore=new JLabel("�ɼ�");
	
	JTextField txtSno=new JTextField(20);
	JTextField txtScore=new JTextField(20);
	
	GridLayout gl=new GridLayout(3,2,12,10);
	JButton add=new JButton("���");
	JButton cancel=new JButton("ȡ��");
	JPanel panNorth=new JPanel();
	JPanel panSorth=new JPanel();
	String ID;
	
	public void add(String Id) { 
		ID=Id;
		frame.setSize(350,300);
		frame.setLocation(700, 450);
		frame.setLayout(new BorderLayout());
		panNorth.setLayout(gl);
		panSorth.setLayout(new FlowLayout());
		panNorth.add(labSno);
		panNorth.add(txtSno);
		panNorth.add(labScore);
		panNorth.add(txtScore);
		
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
			String Sno=txtSno.getText();
			String Score=txtScore.getText();
			String Cno=null;
			DataBase db=new DataBase();
			Connection con=db.getConnection();
			
			try {
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select �γ̺� from Course,Teacher where Teacher.ID='"+ID+"' and Teacher.ID=Course.ID");
				
				if(rs.next()) {
					Cno=rs.getString(1);
				} 
				 
				ResultSet rs1=stmt.executeQuery("select * from Score where �γ̺�='"+Cno+"'and ID ='"+Sno+"'");
				if(rs1.next()) {
					JOptionPane.showMessageDialog(null, "��ѧ����ǰ�γ̳ɼ��Ѿ�¼�룬�����ظ���ӣ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else { 
					stmt.execute("insert into Score(ID,�γ̺�,�ɼ�) values('"+Sno+"','"+Cno+"','"+Score+"')");
					JOptionPane.showMessageDialog(null, "�����ɣ�");
					db.closeConnection(con);
					//frame.dispose();
					//new TeacherMenu().buildMenu();//���ع���Ա����
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
	
