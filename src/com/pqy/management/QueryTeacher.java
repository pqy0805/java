package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.pqy.dao.DBSelecte;
public class QueryTeacher {
	
	JFrame f=new JFrame("教职工信息查询");
	JComboBox cb=new JComboBox();
	JLabel lab=new JLabel("查询方式"); 
	JButton btn=new JButton("查询");
	JButton flash=new JButton("刷新");
	JButton exit=new JButton("退出");
	JTextField txt=new JTextField(15);
	JPanel ptable=new JPanel(); 
	JPanel other=new JPanel();
	JScrollPane sp;
	JTable table=new JTable(); 
	String item;
	String x;
	Vector columnNames=new Vector();
	Vector rowData=new Vector();
	
	public void QueryTeacherUI() {
		f.setSize(500,550);
		f.setLocation(700, 400);
		f.setLayout(new BorderLayout());
		other.setLayout(new FlowLayout());
		
		cb.addItem("-请选择-");
		cb.addItem("教职工号");
		cb.addItem("姓名");
		cb.addItem("性别");
		cb.addItem("课程号");
		cb.addItem("课程名");
		
		other.add(lab);
		other.add(cb);
		other.add(txt);
		other.add(btn);
		other.add(flash);
		
		cb.addItemListener(new MyItemListener());
		btn.addActionListener(new SelectActionListener());
		exit.addActionListener(new ExitListener());
		flash.addActionListener(new FlashListener());
		
		f.add(other,BorderLayout.NORTH);
		 
	    sp=new JScrollPane(table);
		ptable.add(sp); 
		f.add(ptable,BorderLayout.CENTER);
		f.add(new JPanel().add(exit),BorderLayout.SOUTH);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	 void display() {
		String s=txt.getText();
		ResultSet rs=null; 
		DBSelecte d=new DBSelecte();
		rs=d.SelectTeacher(x,s);
		int row = 0;//获得记录的总数
		try {
			while(rs.next()) { 
				row++; 
			}
			if(row==0) {
			JOptionPane.showMessageDialog(null, "没有查询结果","提示",JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			new QueryTeacher().QueryTeacherUI();
		}
		else { 	
			rs.beforeFirst(); 
			columnNames.add("教职工号"); 
			columnNames.add("姓名"); 
			columnNames.add("性别");
			columnNames.add("课程号");
			columnNames.add("课程名");
			while(rs.next()) {
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				rowData.add(hang);
				 
			}	
			DefaultTableModel model=new DefaultTableModel(rowData,columnNames);
			table.setModel(model);
			
		  }
			
		 } catch (SQLException e1) {
			e1.printStackTrace();
		}
		
			
	} 
	 // 退出查询
	class ExitListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new ManagerMenu().buildMenu();
			} 
		 }
	 //查询监听器
	class SelectActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		 display();
		 
	    }
	}
	
	class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			String item=e.getItem().toString();
			
			switch(item) 
			{
			case "教职工号":
				x="Teacher.ID";break;
			case "姓名":
				x="教师名";break;
			case "性别":
				x="性别";break;
			case "课程号":
				x="课程号";break;
			case "课程名":
				x="课程名";break;
			}
		}
		
	}
	
	//刷新界面
		class FlashListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new QueryTeacher().QueryTeacherUI();
			}
			
		}
	/*public static void main(String[] args) {
        new QueryTeacher().QueryTeacherUI();
    }*/

 

}
