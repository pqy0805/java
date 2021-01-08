package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.pqy.dao.DBSelecte;

public class QueryStudent {
	
	JFrame f=new JFrame("学生信息查询");
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
	String x=null; 
	Vector columnNames=new Vector();
	Vector rowData=new Vector();
	
	public void QueryStudentUI() {
		f.setSize(500,550);
		f.setLocation(700, 400);
		f.setLayout(new BorderLayout());
		other.setLayout(new FlowLayout());
		
		cb.addItem("-请选择-"); 
		cb.addItem("学号");
		cb.addItem("姓名");
		cb.addItem("性别");
		cb.addItem("班级号");
		 
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
		rs=d.SelectStudent(x, s);
		int row = 0;//获得记录的总数
		try {
			while(rs.next()) { 
				row++; 
			}
			if(row==0) {
			JOptionPane.showMessageDialog(null, "没有查询结果","提示",JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			new QueryStudent().QueryStudentUI();
		} 
		else { 	
			rs.beforeFirst(); 
			columnNames.add("学号");
			columnNames.add("姓名"); 
			columnNames.add("性别");
			columnNames.add("班级号");
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
		/* if(num!=0) {  
			 f.dispose(); 
		 new QueryStudent().QueryStudentUI();
		 }
		 num++;*/
		 
	    }
	}
	
	class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			String item=e.getItem().toString();
			switch(item) 
			{
			case "学号":
				x="Student.ID";break;
			case "姓名":
				x="姓名";break; 
			case "性别":
				x="性别";break;
			case "班级号":
				x="班级号";break;
				
			}
		} 
		
	}
	//刷新界面
	class FlashListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			f.dispose();
			new QueryStudent().QueryStudentUI();
		}
		
	}
	/*public static void main(String[] args) {
        new QueryStudent().QueryStudentUI(); 
    }*/

 
	}

