package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.pqy.dao.DataBase;
public class AddTeacher {
	JFrame frame=new JFrame("添加教师信息");
	JLabel labId=new JLabel("教师号");
	JLabel labPassword=new JLabel("登录密码");
	JLabel labName=new JLabel("教师名");
	JLabel labCno=new JLabel("课程号");
	JLabel labCname=new JLabel("课程名");
	JLabel labSex=new JLabel("性别 (男或女)"); 
	JTextField txtId=new JTextField(20);
	JTextField txtPassword=new JTextField(20);
	JTextField txtName=new JTextField(20);
	JTextField txtCno=new JTextField(20);
	JTextField txtCname=new JTextField(20); 
	JTextField txtSex=new JTextField(20);
	GridLayout gl=new GridLayout(6,2,12,10);
	JButton add=new JButton("添加"); 
	JButton cancel=new JButton("取消");
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
	//添加信息监听器
	class AddActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String user=txtId.getText();
				String password=txtPassword.getText();
				String name=txtName.getText();
				String Cno=txtCno.getText();
				String Cname=txtCname.getText();
				String sex=txtSex.getText();
				DataBase db=new DataBase();
				if(!sex.equals("男")&&!sex.equals("女")) {
					JOptionPane.showMessageDialog(null, "请输入正确性别！","提示",JOptionPane.INFORMATION_MESSAGE);
				}
				Connection con=db.getConnection();
				try {
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery("select * from Teacher where ID='"+user+"'");
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "该教职工号已重复","提示",JOptionPane.INFORMATION_MESSAGE);
					} 
					else {
						ResultSet rs2=stmt.executeQuery("select * from Course where 课程号='"+Cno+"'");
						if(rs2.next()) {
							JOptionPane.showMessageDialog(null, "该课程号已重复","提示",JOptionPane.INFORMATION_MESSAGE);
					    }
					    else {
						stmt.execute("insert into Teacher(ID,教师名,性别,登录密码) values('"+user+"','"+name+"','"+sex+"','"+password+"')");
						stmt.execute("insert into Course(课程号,课程名,ID) values('"+Cno+"','"+Cname+"','"+user+"')");
						JOptionPane.showMessageDialog(null, "添加完成！");
						db.closeConnection(con);
						frame.dispose();
						new ManagerMenu().buildMenu();//返回管理员界面
					}
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
		new AddTeacher().add();
	}*/
}
