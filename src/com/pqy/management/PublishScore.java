package com.pqy.management;

import javax.swing.*;

import com.pqy.management.PublishCourse.CancelActionListener;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import com.pqy.dao.DataBase;

public class PublishScore {
	JFrame f=new JFrame("成绩表");
	JFrame frame=new JFrame(); 
	JScrollPane sp;
	JTable table;
	JButton btn=new JButton("发布成绩");
	JTextField txt=new JTextField(20);
	JButton cancle=new JButton("取消");
	boolean n;
	Vector rowData,columnNames;
	DataBase db=new DataBase();
	Connection con=db.getConnection(); 
	
	String name;	
	String No="N";
	public void buildUI() {
		   
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
	 
	void isPublish(String CName) {
		try {
			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=stmt.executeQuery("select 状态 from Course where 课程名= '"+CName+"'");
			rs.next(); 
			String p=rs.getString(1);
			 
			if(p.equals("N")) {
			
				stmt.execute("update Course set 状态='Y' where 课程名='"+CName+"'");
				JOptionPane.showMessageDialog(null, "发布成绩成功！！","提示",JOptionPane.INFORMATION_MESSAGE);
			}   
			else 
				JOptionPane.showMessageDialog(null, "成绩已发布！！","提示",JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
				new ManagerMenu().buildMenu();;
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
		
	public void ResultUI() {
		    		    
		    QueryScore(name);
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
	
	class PublishActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			isPublish(name);
		} 
		
	}
	class CancelActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			new ManagerMenu().buildMenu();
		}
	}
	/*public static void main(String[] args) {
        new PublishScore().ResultUI("高等数学");
    }*/


}

