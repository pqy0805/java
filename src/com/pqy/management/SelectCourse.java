package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.pqy.management.QueryStudent.ExitListener;
import com.pqy.management.QueryStudent.SelectActionListener;
import com.pqy.dao.DataBase;
public class SelectCourse {
	JFrame frame=new JFrame("ѡ��");
	JLabel lab=new JLabel("ѡ�������ǵĿΣ�");
	JTextField txt=new JTextField(20);
	JButton btn=new JButton("ѡ��");
	JButton cancel=new JButton("�˳�");
	JPanel ptable=new JPanel(); 
	JPanel other=new JPanel(); 
	JScrollPane sp;
	JTable table=new JTable(); 
	Vector columnNames=new Vector();
	Vector rowData=new Vector();
	String ID=null;
	
	public void SelectCourse(String id) {
		display();
		ID=id;
		frame.setSize(500,550);
		frame.setLocation(700, 400);
		frame.setLayout(new BorderLayout());
		other.setLayout(new FlowLayout());
		
		other.add(lab);
		other.add(txt); 
		other.add(btn);
		
		btn.addActionListener(new SelectActionListener());
		cancel.addActionListener(new ExitListener());
		sp=new JScrollPane(table);
		ptable.add(sp); 
		frame.add(other,BorderLayout.NORTH);
		frame.add(ptable,BorderLayout.CENTER);
		frame.add(new JPanel().add(cancel),BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void display() { 
		//String Cno=txt.getText();
		ResultSet rs=null; 
		DataBase db=new DataBase();
		try {
			int row = 0;//��ü�¼������
			Statement stmt=db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery("select �γ̺�,�γ���,��ʦ��,Teacher.ID from Course,Teacher where Teacher.ID=Course.ID and Course.�γ�״̬='Y'");
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
			columnNames.add("��ְ����");
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
 //ѡ�������
    class SelectActionListener implements ActionListener {
	   public void actionPerformed(ActionEvent e) {
		   String CName=txt.getText();
		   String Cno=null;
		   ResultSet rs=null; 
		   ResultSet rs2=null; 
		   DataBase db=new DataBase();
		   Connection con=db.getConnection();
		   try {
			Statement stmt=con.createStatement();
			rs=stmt.executeQuery("select * from Course where �γ���='"+CName+"'");
			
			if(!rs.next()) {
				JOptionPane.showMessageDialog(null, "û�����ſγ̣���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			}
			
			else {
				//rs.next();
			    Cno=rs.getString(1);
			    rs2=stmt.executeQuery("select �γ��� from Course,Score where Course.�γ̺�=Score.�γ̺� and Score.ID='"+ID+"' and Score.�γ̺�='"+Cno+"'");
				if(rs2.next()) {
					JOptionPane.showMessageDialog(null, "���ſ��Ѿ�ѡ��������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					stmt.execute("insert into Score(ID,�γ̺�)  values ('"+ID+"','"+Cno+"')");
				JOptionPane.showMessageDialog(null, "ѡ�γɹ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			    
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	  
    }
 }
   /* public static void main(String[] args) {
    	new SelectCourse().SelectCourse("20190001");
    }*/
}
