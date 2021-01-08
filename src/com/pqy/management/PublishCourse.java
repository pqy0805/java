package com.pqy.management;

import javax.swing.*;

import com.pqy.management.PublishScore.PublishActionListener;
import com.pqy.management.PublishScore.QueryListener;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import com.pqy.dao.DataBase;
public class PublishCourse {
	JFrame f=new JFrame("�γ�");
	JFrame frame=new JFrame(); 
	JScrollPane sp;
	JTable table;
	JButton btn=new JButton("�����γ���Ϣ");
	JButton cancle=new JButton("ȡ��");
	JTextField txt=new JTextField(20);
	boolean n;
	Vector rowData,columnNames;
	DataBase db=new DataBase();
	Connection con=db.getConnection(); 
	String name;	

	public void buildUI() {
		   
		    JButton query=new JButton("��ѯ");
		   
		    JLabel lab=new JLabel("������γ���");
	    	frame.setSize(300,200);
	    	frame.setLocation(700, 450); 
	    	frame.setLayout(new FlowLayout());
	    	
	    	frame.add(lab); 
	    	frame.add(txt);
	    	frame.add(query); 
	    	
	    	query.addActionListener(new QueryListener());
	    	frame.setVisible(true);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    } 
	 
	 void isPublish(String CName) {
			try {
				Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs=stmt.executeQuery("select �γ�״̬ from Course where �γ���= '"+CName+"'");
				rs.next(); 
				String p=rs.getString(1); 
				
				if(p.equalsIgnoreCase("N")) {
					stmt.execute("update Course set �γ�״̬='Y' where �γ���='"+CName+"'");
					JOptionPane.showMessageDialog(null, "�����γ̳ɹ�����","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}   
				else 
					JOptionPane.showMessageDialog(null, "�γ��ѷ�������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				f.dispose();
					new ManagerMenu().buildMenu();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
		}
	 
	 void QueryCourse(String CName) {
			n=true;
			try {
				Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs=stmt.executeQuery("select �γ̺�,�γ���,Teacher.ID,��ʦ�� from Course,Teacher where �γ���= '"+CName+"' and Teacher.ID=Course.ID");
				int row = 0;//��ü�¼������
				while(rs.next()) { 
					row++;  
				} 
				if(row==0) {
					JOptionPane.showMessageDialog(null, "û�в�ѯ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					n=false;
				}
				else {
					columnNames=new Vector(); 
					columnNames.add("�γ�");
					columnNames.add("�γ���"); 
					columnNames.add("��ʦ��");
					columnNames.add("��ʦ��");
					
					rs.beforeFirst();
					rowData=new Vector();
					while(rs.next()) {
						Vector hang=new Vector();
						hang.add(rs.getString(1));
						hang.add(rs.getString(2)); 
						hang.add(rs.getString(3));
						hang.add(rs.getString(4));
						rowData.add(hang);
					}
				  } 
		      	}catch (SQLException e) {
					e.printStackTrace();
			   } 
	}
	 
	 public void ResultUI() {
		    
		    QueryCourse(name);
		    if(n==false) {
		    	new ManagerMenu().buildMenu();
		    }
		    else {
		    	table=new JTable(rowData,columnNames);
			    f.setSize(400,500);
			    f.setLocation(700, 400);
			    sp=new JScrollPane(table);
			    JPanel p=new JPanel();
			    p.setLayout(new FlowLayout());
			    p.add(btn);
			    p.add(cancle);
			    cancle.addActionListener(new CancelActionListener());
			    f.add(sp,BorderLayout.NORTH);
			    f.add(p,BorderLayout.SOUTH);
			    btn.addActionListener(new PublishActionListener());
			    f.setVisible(true);
			    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    }
	}
	 class QueryListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				name=txt.getText();
				ResultUI();
				frame.dispose();
			} 
		}
	 class CancelActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new ManagerMenu().buildMenu();
			}
		}
	 
	 class PublishActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				isPublish(name);
			} 
			
		}
}
