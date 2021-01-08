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
	JFrame frame=new JFrame("ѡ�����");
	JButton exit=new JButton("�˳�");
	
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
			int row = 0;//��ü�¼������
			Statement stmt=db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select Score.�γ̺�,�γ���,��ʦ��,Teacher.ID from Score,Course,Teacher where Score.ID='"+ID+"'and Score.�γ̺�=Course.�γ̺� and �γ�״̬='Y' and Teacher.ID=Course.ID");
			while(rs.next()) { 
				row++; 
			}
			if(row==0) {
			JOptionPane.showMessageDialog(null, "û�в�ѯ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			frame.dispose();
		} 
		else { 	
			rs.beforeFirst(); 
			columnNames.add("�γ̺�");
			columnNames.add("�γ���"); 
			columnNames.add("�ڿ���ʦ");
			columnNames.add("��ְ��ID");
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
