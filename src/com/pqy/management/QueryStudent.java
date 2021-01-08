package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.pqy.dao.DBSelecte;

public class QueryStudent {
	
	JFrame f=new JFrame("ѧ����Ϣ��ѯ");
	JComboBox cb=new JComboBox();
	JLabel lab=new JLabel("��ѯ��ʽ");
	JButton btn=new JButton("��ѯ");
	JButton flash=new JButton("ˢ��");
	JButton exit=new JButton("�˳�");
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
		
		cb.addItem("-��ѡ��-"); 
		cb.addItem("ѧ��");
		cb.addItem("����");
		cb.addItem("�Ա�");
		cb.addItem("�༶��");
		 
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
		int row = 0;//��ü�¼������
		try {
			while(rs.next()) { 
				row++; 
			}
			if(row==0) {
			JOptionPane.showMessageDialog(null, "û�в�ѯ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			new QueryStudent().QueryStudentUI();
		} 
		else { 	
			rs.beforeFirst(); 
			columnNames.add("ѧ��");
			columnNames.add("����"); 
			columnNames.add("�Ա�");
			columnNames.add("�༶��");
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
	 // �˳���ѯ
	class ExitListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				f.dispose();
				new ManagerMenu().buildMenu();
			} 
		 }
	 //��ѯ������
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
			case "ѧ��":
				x="Student.ID";break;
			case "����":
				x="����";break; 
			case "�Ա�":
				x="�Ա�";break;
			case "�༶��":
				x="�༶��";break;
				
			}
		} 
		
	}
	//ˢ�½���
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

