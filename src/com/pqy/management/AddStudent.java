package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.pqy.dao.DataBase;
public class AddStudent {
	JFrame frame=new JFrame("添加学生信息"); 
	JLabel labId=new JLabel("学号"); 
	JLabel labPassword=new JLabel("登录密码");
	JLabel labName=new JLabel("姓名");
	JLabel labClass=new JLabel("班级号");
	JLabel labDate=new JLabel("出生日期");
	JLabel labSex=new JLabel("性别 (男或女)");
	//JLabel labPro=new JLabel("专业");
	JTextField txtId=new JTextField(20);
	JTextField txtPassword=new JTextField(20); 
	JTextField txtName=new JTextField(20);
	JTextField txtClass=new JTextField(20);
	JTextField txtDate=new JTextField(20);
	JTextField txtSex=new JTextField(20);
	JTextField txtPro=new JTextField(20);
	GridLayout gl=new GridLayout(7,2,12,10);
	JButton add=new JButton("添加");
	JButton cancel=new JButton("取消");
	JPanel panNorth=new JPanel();
	JPanel panSorth=new JPanel();
	String user;
	String password;
	String name;
	String Class;
	String Date;
	String sex;
	String pro;
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
		panNorth.add(labClass);
		panNorth.add(txtClass);
		//panNorth.add(labPro);
		//panNorth.add(txtPro); 
		panNorth.add(labDate);
		panNorth.add(txtDate);
		panSorth.add(add);
		panSorth.add(cancel);
		add.addActionListener(new AddActionListener());
		cancel.addActionListener(new CancelActionListener());
		frame.add(panNorth,BorderLayout.NORTH);
		frame.add(panSorth,BorderLayout.SOUTH);
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			 user=txtId.getText();
			 password=txtPassword.getText();
			 name=txtName.getText();
			 Class=txtClass.getText();
			 Date=txtDate.getText();
			 sex=txtSex.getText();
			 pro=txtPro.getText();
			DataBase db=new DataBase();
			if(!sex.equals("男")&&!sex.equals("女")) {
				JOptionPane.showMessageDialog(null, "请输入正确性别！","提示",JOptionPane.INFORMATION_MESSAGE);
			} 
			Connection con=db.getConnection();
			try {
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select * from Student where ID='"+user+"'");
				if(rs.next()) {
					JOptionPane.showMessageDialog(null, "该学号已存在","提示",JOptionPane.INFORMATION_MESSAGE);
				} 
				else {
					stmt.execute("insert into Student(ID,姓名,性别,出生日期,班级号,登录密码) values('"+user+"','"+name+"','"+sex+"','"+Date+"','"+Class+"','"+password+"')");
					 
					JOptionPane.showMessageDialog(null, "添加完成！");
					db.closeConnection(con);
					frame.dispose();
					new ManagerMenu().buildMenu();//返回管理员界面
				}
					
			} catch (SQLException e1) {
				e1.printStackTrace(); 
			} 
			
		}
	}
           //取消 监听器	 
        class CancelActionListener implements ActionListener{
         	public void actionPerformed(ActionEvent e) {
		    frame.dispose();
		    new ManagerMenu().buildMenu();
	} 
	
  }
        /*public static void main(String[] args) {
        	new AddStudent().add();
        }*/ 
    		

}
