package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerMenu {
	 JFrame frame=new JFrame("管理员界面");
	 JLabel labQuery=new JLabel("查询");
	 JButton qStudent=new JButton("查看学生信息");
	 JButton qTeacher=new JButton("查看教职工信息");
	 JButton qScore=new JButton("查看成绩");
	 
	 JLabel labChange=new JLabel("修改");
	 JButton aStudent=new JButton("添加学生信息");
	 JButton aTeacher=new JButton("添加教职工信息");
	 JButton aScore=new JButton("发布成绩");
	 JButton aCourse=new JButton("发布课程信息");
	 
	 JLabel labOther=new JLabel("其他");
	 JButton cPassword=new JButton("修改密码");
	 JButton exit=new JButton("退出");
	 String Id;
	 //获得用户ID
	 public void User(String id) {
		 Id=new String(id);	 
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
		 frame.add(aTeacher);aTeacher.setBounds(45, 160, 140, 30);
		 frame.add(aStudent);aStudent.setBounds(205, 160, 140, 30);
		 frame.add(aCourse);aCourse.setBounds(45, 200, 140, 30);
		 frame.add(aScore);aScore.setBounds(205, 200, 140, 30);
		 
		 frame.add(labOther);labOther.setBounds(13,240,40,50);
		 frame.add(cPassword);cPassword.setBounds(45,280,140,30);
		 frame.add(exit);exit.setBounds(205,280,140,30);
		 
		 qTeacher.addActionListener(new QueryTeacherActionListener());
		 qStudent.addActionListener(new QueryStudentActionListener());
		 qScore.addActionListener(new QueryScoreActionListener());
		 
		 aTeacher.addActionListener(new AddTeacherActionListener());
		 aStudent.addActionListener(new AddStudentActionListener());
		 aScore.addActionListener(new AddScoreActionListener());
		 aCourse.addActionListener(new AddCourseActionListener());
		 
		 cPassword.addActionListener(new ChangerPasswordListener());
		 exit.addActionListener(new ExitListener()); 
		 
		 frame.setVisible(true); 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
	 }   
	 
	 class ExitListener implements ActionListener{// 退出
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		} 
	 }
	 
     class ChangerPasswordListener implements ActionListener{//修改密码监视器
		public void actionPerformed(ActionEvent e) {
			 new ChangePassword().ChangeUI(Id, "Manager");
			 frame.dispose();
		}
		 
	 }
	 
	 class AddCourseActionListener implements ActionListener{//发布课程信息监视器
	     public void actionPerformed(ActionEvent e) {
	    	new PublishCourse().buildUI();
	    	frame.dispose();
	     }
	}
	 
	 class AddScoreActionListener implements ActionListener{//发布成绩监视器
	     public void actionPerformed(ActionEvent e) {
	         new PublishScore().buildUI();;  	
	    	 frame.dispose();
	     }
	}
	 
	 class AddTeacherActionListener implements ActionListener{//添加教师信息监视器
	     public void actionPerformed(ActionEvent e) {
	    	 new AddTeacher().add();
	    	 frame.dispose();
	     }
	}
	 
	 class AddStudentActionListener implements ActionListener{//添加学生信息监视器
	     public void actionPerformed(ActionEvent e) {
	    	 new AddStudent().add();
	    	 frame.dispose(); 
	    	 
	     }
	}
	 
	 class QueryTeacherActionListener implements ActionListener{// 查询老师信息监视器
		public void actionPerformed(ActionEvent e) {
			new QueryTeacher().QueryTeacherUI();
			frame.dispose();
		}
	 } 
	 
	 class QueryStudentActionListener implements ActionListener{// 查询学生信息监视器
		public void actionPerformed(ActionEvent e) {
			new QueryStudent().QueryStudentUI();
			frame.dispose();
			}  
			 
		 }
	 
	 class QueryScoreActionListener implements ActionListener{// 查询成绩监视器
		public void actionPerformed(ActionEvent e) {
			new UserQueryScore().buildUI(1);
			frame.dispose();
			
			}
		 }
	
	 /*public static void main(String[] args) {
		 new ManagerMenu().buildMenu(); 
	 }*/

}
