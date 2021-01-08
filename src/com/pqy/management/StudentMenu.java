package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentMenu {
	JFrame frame=new JFrame("ѧ������");
	 JLabel labQuery=new JLabel("��ѯ");
	 JButton qStudent=new JButton("�鿴������Ϣ");
	 JButton qCourse=new JButton("�鿴ѡ�����");
	 JButton qScore=new JButton("�鿴�ɼ�");
	 
	 JLabel labChange=new JLabel("�޸�");
	 JButton aCourse=new JButton("ѡ��");
	 JButton cPassword=new JButton("�޸�����");
	 
	 JLabel labOther=new JLabel("����");
	 JButton relogin=new JButton("���µ�¼");
	 JButton exit=new JButton("�˳�");
	 
	 static String Id;
	 
	 public void User(String id) {
		 Id=id;	 
	 }
	 
	 public void buildMenu() {
		 frame.setSize(400,400);
		 frame.setLocation(700, 450);
		 frame.setLayout(null);
		 labQuery.setFont(new Font("����",Font.BOLD,17));
		 labChange.setFont(new Font("����",Font.BOLD,17));
		 labOther.setFont(new Font("����",Font.BOLD,17));
		 frame.add(labQuery);labQuery.setBounds(13, 8, 40, 50);
		 frame.add(qStudent);qStudent.setBounds(45, 50,140, 30);
		 frame.add(qCourse);qCourse.setBounds(205, 50, 140, 30);
		 frame.add(qScore);qScore.setBounds(45, 90, 140, 30);
		 
		 frame.add(labChange);labChange.setBounds(13,125,40,50);
		 frame.add(aCourse);aCourse.setBounds(45, 160, 140, 30);
		 frame.add(cPassword);cPassword.setBounds(205, 160, 140, 30);
		 
		 
		 frame.add(labOther);labOther.setBounds(13,240,40,50);
		 frame.add(relogin);relogin.setBounds(45,280,140,30);
		 frame.add(exit);exit.setBounds(205,280,140,30);
		 
		 qCourse.addActionListener(new QueryCourseListener());
		 qStudent.addActionListener(new QueryStudentListener());
		 qScore.addActionListener(new QueryScoreListener());
		 
		 aCourse.addActionListener(new AddCourseListener());
		 cPassword.addActionListener(new ChangerPasswordListener());
		 
		 relogin.addActionListener(new ReLoginListener());
		 exit.addActionListener(new ExitListener()); 
		 
		 frame.setVisible(true); 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
	 }   
	 class QueryCourseListener implements ActionListener{//�鿴ѡ�����
		public void actionPerformed(ActionEvent e) {
			new QueryCourse().QueryCourse(Id);
			frame.dispose();
		}
	 }
	 
	 class AddCourseListener implements ActionListener{//ѡ��
		public void actionPerformed(ActionEvent e) {
			new SelectCourse().SelectCourse(Id);
			frame.dispose();
		}
	 } 
	 
     class QueryScoreListener implements ActionListener{//�鿴�ɼ�
		public void actionPerformed(ActionEvent e) {
			QueryScore q=new QueryScore();
			q.display(Id);
			q.QueryScore();
			frame.dispose();
		}
	 }
	 
	 class ExitListener implements ActionListener{// �˳�
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			} 
		 }
	 
	 class ReLoginListener implements ActionListener{//���µ�¼
			public void actionPerformed(ActionEvent e) {
				new LoginUI().Init();
				frame.dispose();
			} 
	  }
	
	 class QueryStudentListener implements ActionListener{//��ѯѧ��������Ϣ
			public void actionPerformed(ActionEvent e) {
				new QueryUser().QueryStudent(Id, 2);
				frame.dispose();
			}
			   
		  }
	 
	 class ChangerPasswordListener implements ActionListener{//�޸����������
			public void actionPerformed(ActionEvent e) {
				 new ChangePassword().ChangeUI(Id, "Student");
				 frame.dispose();
			}
			 
		 }
}
