package com.pqy.management;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import com.pqy.dao.DBSelecte;
import com.pqy.dao.DataBase;
public class QueryUser {
	
	JFrame frame=new JFrame("������Ϣ����");
	JFrame f=new JFrame();
	JTextField txt=new JTextField(20); 
	JTable table;
	JPanel ptable=new JPanel();
	JPanel other=new JPanel(); 
	JButton back=new JButton("����");
	JScrollPane sp;
	
	Vector columnNames=new Vector();
	Vector rowData=new Vector();
	String ID,type;
	int num;
	
	public void InUI(String Type) {
		type=Type;
	    JButton query=new JButton("��ѯ");
	   
	    JLabel lab=new JLabel("������ID");
    	f.setSize(300,200);
    	f.setLocation(700, 450); 
    	f.setLayout(new FlowLayout());
    	 
    	f.add(lab); 
    	f.add(txt);
    	f.add(query); 
    	
    	query.addActionListener(new QueryListener());
    	f.setVisible(true);
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
 
	void QueryTeacher(String Id,int Num) {
		JLabel labId=new JLabel("��ʦ��");
		JLabel labName=new JLabel("����");
		JLabel labSex=new JLabel("�Ա�");
		JLabel labCno=new JLabel("�γ̺�");
		JLabel labCName=new JLabel("�γ���");
		JLabel id = null;
		JLabel Name = null;
		JLabel Sex = null;
		JLabel Cno = null; 
		JLabel CName = null; 
		num=Num;
		DataBase db=new DataBase();
		Connection con=db.getConnection();
	    //rs1=d.SelectTeacher("Teacher.ID", Id);
		
	    try {
	    	Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs=null;
		    ResultSet rs2=null;
		    rs=stmt.executeQuery("select Teacher.ID,��ʦ��,�Ա�,�γ̺�,�γ��� from Teacher,Course where Teacher.ID ='"+Id+"'and Teacher.ID=Course.ID ");
		   
		    if(rs.next()) {
		    	id=new JLabel(rs.getString(1));
				Name=new JLabel(rs.getString(2));
				Sex=new JLabel(rs.getString(3));
				Cno=new JLabel(rs.getString(4)); 
				CName=new JLabel(rs.getString(5));  
		    }
		    
		    else {
		    	 rs2=stmt.executeQuery("select Teacher.ID,��ʦ��,�Ա� from Teacher,Course where Course.ID is Null and Teacher.ID ='"+Id+"'");
		    	if(rs2.next()) {
		    id=new JLabel(rs2.getString(1));
			Name=new JLabel(rs2.getString(2));
			Sex=new JLabel(rs2.getString(3));
			Cno=new JLabel(" "); 
			CName=new JLabel(" ");
		    	}
			else
		    {
		    	JOptionPane.showMessageDialog(null, "û���ҵ�","��ʾ ",JOptionPane.INFORMATION_MESSAGE);
		    }
		    
		    }
		    
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		
		frame.setSize(500,350);
		frame.setLocation(700, 450);
		frame.setLayout(new BorderLayout());
		
		ptable.setLayout(new GridLayout(5,2,15,5));
	    ptable.add(labId);ptable.add(id);
	    ptable.add(labName);ptable.add(Name);
	    ptable.add(labSex);ptable.add(Sex);
	    ptable.add(labCno);ptable.add(Cno);
	    ptable.add(labCName);ptable.add(CName);
	    
    	other.add(back);

    	frame.add(new JPanel(),BorderLayout.NORTH);
    	frame.add(ptable,BorderLayout.CENTER);
    	frame.add(other,BorderLayout.SOUTH);
    	back.addActionListener(new BackListener());
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
	}
	
	void QueryStudent(String Id,int Num) {
	
		JLabel labId=new JLabel("ѧ��");
		JLabel labName=new JLabel("����");
		JLabel labSex=new JLabel("�Ա�");
		JLabel labClass=new JLabel("�༶");
		
		JLabel id = null;
		JLabel Name = null;
		JLabel Sex = null;
		JLabel Class = null; 
		
		num=Num;
		ResultSet rs1=null; 
		DBSelecte d=new DBSelecte();
	    rs1=d.SelectStudent("Student.ID", Id);
	    try {
		    if(rs1.next()) {
		    id=new JLabel(rs1.getString(1));
			Name=new JLabel(rs1.getString(2));
			Sex=new JLabel(rs1.getString(3));
			Class=new JLabel(rs1.getString(4)); 
			
		    }
		    
		    else
		    {
		    	JOptionPane.showMessageDialog(null, "û���ҵ�","��ʾ ",JOptionPane.INFORMATION_MESSAGE);
		    }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		
		frame.setSize(500,350);
		frame.setLocation(700, 450);
		frame.setLayout(new BorderLayout());
		
		ptable.setLayout(new GridLayout(4,2,15,5));
	    ptable.add(labId);ptable.add(id);
	    ptable.add(labName);ptable.add(Name);
	    ptable.add(labSex);ptable.add(Sex);
	    ptable.add(labClass);ptable.add(Class);
	    
    	other.add(back);

    	frame.add(new JPanel(),BorderLayout.NORTH);
    	frame.add(ptable,BorderLayout.CENTER);
    	frame.add(other,BorderLayout.SOUTH);
    	back.addActionListener(new BackListener());
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
	}
	
	class QueryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ID=txt.getText();
			if("S".equalsIgnoreCase(type)) {
				QueryStudent(ID,1);
			}
			 else if("T".equalsIgnoreCase(type)) {
				QueryTeacher(ID,2);
			}
			f.dispose();  
		} 
	 }
	
	class BackListener implements ActionListener{//������һ����
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			if(num==1) {
				new TeacherMenu().buildMenu();
			}
			else if(num==2) {
				new StudentMenu().buildMenu();
			}
			
		}
		 
	 }
	
	/* public static void main(String[] args) {
		new QueryUser().QueryTeacher("t101",1);
	 }*/
}
