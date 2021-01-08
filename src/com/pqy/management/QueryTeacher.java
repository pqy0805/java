package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.pqy.dao.DBSelecte;
public class QueryTeacher {
	
	JFrame f=new JFrame("��ְ����Ϣ��ѯ");
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
	String x;
	Vector columnNames=new Vector();
	Vector rowData=new Vector();
	
	public void QueryTeacherUI() {
		f.setSize(500,550);
		f.setLocation(700, 400);
		f.setLayout(new BorderLayout());
		other.setLayout(new FlowLayout());
		
		cb.addItem("-��ѡ��-");
		cb.addItem("��ְ����");
		cb.addItem("����");
		cb.addItem("�Ա�");
		cb.addItem("�γ̺�");
		cb.addItem("�γ���");
		
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
		int row = 0;//��ü�¼������
		try {
			while(rs.next()) { 
				row++; 
			}
			if(row==0) {
			JOptionPane.showMessageDialog(null, "û�в�ѯ���","��ʾ",JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			new QueryTeacher().QueryTeacherUI();
		}
		else { 	
			rs.beforeFirst(); 
			columnNames.add("��ְ����"); 
			columnNames.add("����"); 
			columnNames.add("�Ա�");
			columnNames.add("�γ̺�");
			columnNames.add("�γ���");
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
		 
	    }
	}
	
	class MyItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			String item=e.getItem().toString();
			
			switch(item) 
			{
			case "��ְ����":
				x="Teacher.ID";break;
			case "����":
				x="��ʦ��";break;
			case "�Ա�":
				x="�Ա�";break;
			case "�γ̺�":
				x="�γ̺�";break;
			case "�γ���":
				x="�γ���";break;
			}
		}
		
	}
	
	//ˢ�½���
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
