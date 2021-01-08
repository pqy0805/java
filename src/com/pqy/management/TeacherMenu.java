package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherMenu {
	 JFrame frame=new JFrame("教职工界面");
	 JLabel labQuery=new JLabel("查询");
	 JButton qStudent=new JButton("查看学生信息");
	 JButton qTeacher=new JButton("查看教职工信息");
	 JButton qScore=new JButton("查看成绩");
	 
	 JLabel labChange=new JLabel("修改");
	 JButton aCourse=new JButton("添加课程");
	 JButton aScore=new JButton("录入成绩");
	 JButton cPassword=new JButton("修改密码");
	 
	 JLabel labOther=new JLabel("其他");
	 JButton relogin=new JButton("重新登录");
	 JButton exit=new JButton("退出");

	 static String Id;
	 //获得用户ID
	 public void User(String id) {
		 Id=id;	 
	 }
	 
	 public void buildMenu() {
		 frame.setSize(400,400);
		 frame.setLocation(700, 450);
		 frame.setLayout(null);
		 labQuery.setFont(new Font("宋体",Font.BOLD,17));
		 labChange.setFont(new Font("宋体",Font.BOLD,17));
		 labOther.setFont(new Font("宋体",Font.BOLD,17));
		 frame.add(labQuery);labQuery.setBounds(13, 8, 40, 50);
		 frame.add(qTeacher);qTeacher.setBounds(45, 50,140, 30);
		 frame.add(qStudent);qStudent.setBounds(205, 50, 140, 30);
		 frame.add(qScore);qScore.setBounds(45, 90, 140, 30);
		 
		 frame.add(labChange);labChange.setBounds(13,125,40,50);
		 frame.add(aCourse);aCourse.setBounds(45, 160, 140, 30);
		 frame.add(aScore);aScore.setBounds(205, 160, 140, 30);
		 frame.add(cPassword);cPassword.setBounds(45, 200, 140, 30);
		 
		 frame.add(labOther);labOther.setBounds(13,240,40,50);
		 frame.add(relogin);relogin.setBounds(45,280,140,30);
		 frame.add(exit);exit.setBounds(205,280,140,30);
		 
		 qTeacher.addActionListener(new QueryTeacherListener());
		 qStudent.addActionListener(new QueryStudentListener());
		 qScore.addActionListener(new QueryScoreListener());
		 
		 aScore.addActionListener(new AddScoreListener());
		 aCourse.addActionListener(new AddCourseListener());
		 cPassword.addActionListener(new ChangerPasswordListener());
		 
		 relogin.addActionListener(new ReLoginListener());
		 exit.addActionListener(new ExitListener()); 
		 
		 frame.setVisible(true); 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
	 }   
	 
	   class ReLoginListener implements ActionListener{//重新登录
		public void actionPerformed(ActionEvent e) {
			new LoginUI().Init();
			frame.dispose();
		}
	  }
	   
       class AddCourseListener implements ActionListener{//添加新课程
		public void actionPerformed(ActionEvent e) {
			new AddCourse().add();
			frame.dispose();
		}
		   
	   }
	   
	   class AddScoreListener implements ActionListener{//录入成绩
			public void actionPerformed(ActionEvent e) {
				new AddScore().add(Id);
				frame.dispose();
			  }
			   
		   }
	 
	   class QueryTeacherListener implements ActionListener{//查询老师个人信息
		public void actionPerformed(ActionEvent e) {
			new QueryUser().QueryTeacher(Id,1);
			frame.dispose();
		  }
		   
	   }
	   
	   class QueryStudentListener implements ActionListener{//查询学生信息
			public void actionPerformed(ActionEvent e) {
				new QueryUser().InUI("S");
				frame.dispose();
			}
			   
		  }
	  
	   class QueryScoreListener implements ActionListener{//查看成绩
			public void actionPerformed(ActionEvent e) {
				new UserQueryScore().buildUI(2);
				frame.dispose();
			}
		  }
	 
	   class ExitListener implements ActionListener{// 退出
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			} 
		 }
	   
	   class ChangerPasswordListener implements ActionListener{//修改密码监视器
			public void actionPerformed(ActionEvent e) {
				 new ChangePassword().ChangeUI(Id, "Teacher");
				 frame.dispose();
			}
			 
		 }
}
