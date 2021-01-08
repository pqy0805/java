package com.pqy.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import com.pqy.dao.DataBase;
public class QueryScore {
	JFrame frame=new JFrame("���˳ɼ�");
	JButton fail=new JButton("δ����");
	JButton cancel=new JButton("����");
	JButton save=new JButton("����ɼ���");
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
			int row = 0;//��ü�¼������
			Statement stmt=db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select Score.�γ̺�,�γ���,�ɼ�,״̬ from Score,Course where Score.ID='"+ID+"'and Score.�γ̺�=Course.�γ̺� and ״̬='Y'");
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
			columnNames.add("�ɼ�");
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
			int row = 0;//��ü�¼������
			Statement stmt=db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select Score.�γ̺�,�γ���,�ɼ�,״̬ from Score,Course where Score.ID='"+ID+"'and Score.�γ̺�=Course.�γ̺� and ״̬='Y' and �ɼ�<60 and �ɼ� is not Null");
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
			columnNames.add("�ɼ�");
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
			JOptionPane.showMessageDialog(null, "����óɼ���ɹ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
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
