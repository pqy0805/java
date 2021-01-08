package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import com.pqy.dao.DataBase;


public class UserQueryScore {
	JFrame f=new JFrame("查询成绩");
	JFrame frame=new JFrame();
	JTable table=new JTable();
	JScrollPane sp=new JScrollPane();
	JButton btn=new JButton("返回");
	JTextField txt=new JTextField(20);
    DataBase db=new DataBase();
	Connection con=db.getConnection(); 
	Vector rowData,columnNames;
	String name;
	boolean n,m; 
	int Num;
	String No="N"; 
	 void buildUI(int num) {
		    Num=num;
		    JButton query=new JButton("查询");
		   
		    JLabel lab=new JLabel("请输入课程名");
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
	 
    void ResultUI() { 
    	QueryScore(name);
    	 if(n==false) {
    		 if(Num==1) {
 				new ManagerMenu().buildMenu();
 			}
     		else if(Num==2) {
     			new TeacherMenu().buildMenu();
     		}
		}
    	 else {
		table=new JTable(rowData,columnNames);
		f.setSize(400,500);
		f.setLocation(700, 400);
		sp=new JScrollPane(table);
		f.add(sp,BorderLayout.NORTH);
		f.add(btn,BorderLayout.SOUTH);
		btn.addActionListener(new BackListener());
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 }
   }
    
    void isPublish(String CName) {
    	m=true;
    	;
		try {
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=stmt.executeQuery("select 状态 from Course where 课程名= '"+CName+"'");
			if(rs.next()) {
				String p=rs.getString(1);
				 
				if(p.equals("N")) {
				JOptionPane.showMessageDialog(null, "当前成绩还未发布请耐心等待！！","提示",JOptionPane.INFORMATION_MESSAGE);
				m=false;  		 
			} 
			}
			else {
				JOptionPane.showMessageDialog(null, "没有该门课程！！","提示",JOptionPane.INFORMATION_MESSAGE);
				m=false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
    void QueryScore(String CName) {
    	n=true;
		try {
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=stmt.executeQuery("select Student.ID,姓名,成绩 from Course,Score,Student where 课程名= '"+CName+"' and Course.课程号=Score.课程号 and Score.ID=Student.ID");
			int row = 0;//获得记录的总数
			while(rs.next()) { 
				row++;  
			} 
			if(row==0) {
				JOptionPane.showMessageDialog(null, "没有查询结果","提示",JOptionPane.INFORMATION_MESSAGE);
				n=false; 
			} 
			else {
				columnNames=new Vector(); 
				columnNames.add("学号");
				columnNames.add("姓名");
				columnNames.add("成绩");
				rs.beforeFirst();
				rowData=new Vector();
				while(rs.next()) {
					Vector hang=new Vector();
					hang.add(rs.getString(1));
					hang.add(rs.getString(2)); 
					hang.add(rs.getString(3));
					
					rowData.add(hang);
				}
			  } 
	      	}catch (SQLException e) {
				e.printStackTrace();
		   } 
}
    
    class BackListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			if(Num==1) {
				new ManagerMenu().buildMenu();
			}
			else if(Num==2) {
				new TeacherMenu().buildMenu();
			}
			
		}
    }
    
    class QueryListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			name=txt.getText();
			isPublish(name);
			if(m==true) { 
				ResultUI();
				frame.dispose();
			}
			else {
				frame.dispose();
        		if(Num==1) {
    				new ManagerMenu().buildMenu();
    			}
        		else if(Num==2) {
        			new TeacherMenu().buildMenu();
        		}
			}
			
		} 
	}
	/*public static void main(String[] args) {
		new UserQueryScore().qs.buildUI();
	}*/

}
