package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.pqy.dao.DataBase;
public class AddTeacher {
	JFrame frame=new JFrame("��ӽ�ʦ��Ϣ");
	JLabel labId=new JLabel("��ʦ��");
	JLabel labPassword=new JLabel("��¼����");
	JLabel labName=new JLabel("��ʦ��");
	JLabel labCno=new JLabel("�γ̺�");
	JLabel labCname=new JLabel("�γ���");
	JLabel labSex=new JLabel("�Ա� (�л�Ů)"); 
	JTextField txtId=new JTextField(20);
	JTextField txtPassword=new JTextField(20);
	JTextField txtName=new JTextField(20);
	JTextField txtCno=new JTextField(20);
	JTextField txtCname=new JTextField(20); 
	JTextField txtSex=new JTextField(20);
	GridLayout gl=new GridLayout(6,2,12,10);
	JButton add=new JButton("���"); 
	JButton cancel=new JButton("ȡ��");
	JPanel panNorth=new JPanel();
	JPanel panSorth=new JPanel();
	
	 public void add() {
		frame.setSize(350,300);
		frame.setLocation(700, 450);
		frame.setLayout(new BorderLayout());
		panNorth.setLayout(gl);
		panSorth.setLayout(new FlowLayout());
		panNorth.add(labId);
		panNorth.add(txtId);
		panNorth.add(labPassword);
		panNorth.add(txtPassword);
		panNorth.add(labName);
		panNorth.add(txtName); 
		panNorth.add(labSex);
		panNorth.add(txtSex);
		panNorth.add(labCno);  
		panNorth.add(txtCno);
		panNorth.add(labCname);
		panNorth.add(txtCname);
		panSorth.add(add);
		panSorth.add(cancel);
		add.addActionListener(new AddActionListener());
		cancel.addActionListener(new CancelActionListener());
		frame.add(panNorth,BorderLayout.NORTH);
		frame.add(panSorth,BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	} 
	//�����Ϣ������
	class AddActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String user=txtId.getText();
				String password=txtPassword.getText();
				String name=txtName.getText();
				String Cno=txtCno.getText();
				String Cname=txtCname.getText();
				String sex=txtSex.getText();
				DataBase db=new DataBase();
				if(!sex.equals("��")&&!sex.equals("Ů")) {
					JOptionPane.showMessageDialog(null, "��������ȷ�Ա�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				Connection con=db.getConnection();
				try {
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from Teacher where ID='"+user+"'");
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "�ý�ְ�������ظ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					} 
					else {
						ResultSet rs2=stmt.executeQuery("select * from Course where �γ̺�='"+Cno+"'");
						if(rs2.next()) {
							JOptionPane.showMessageDialog(null, "�ÿγ̺����ظ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
					    }
					    else {
						stmt.execute("insert into Teacher(ID,��ʦ��,�Ա�,��¼����) values('"+user+"','"+name+"','"+sex+"','"+password+"')");
						stmt.execute("insert into Course(�γ̺�,�γ���,ID) values('"+Cno+"','"+Cname+"','"+user+"')");
						JOptionPane.showMessageDialog(null, "�����ɣ�");
						db.closeConnection(con);
						frame.dispose();
						new ManagerMenu().buildMenu();//���ع���Ա����
					}
				}
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
				
			
			}
					
		}
	//ȡ�� ������	 
	class CancelActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
			new ManagerMenu().buildMenu();
		} 
		
	}

	/*public static void main(String[] args) {
		new AddTeacher().add();
	}*/
}
