package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherMenu {
	 JFrame frame=new JFrame("��ְ������");
	 JLabel labQuery=new JLabel("��ѯ");
	 JButton qStudent=new JButton("�鿴ѧ����Ϣ");
	 JButton qTeacher=new JButton("�鿴��ְ����Ϣ");
	 JButton qScore=new JButton("�鿴�ɼ�");
	 
	 JLabel labChange=new JLabel("�޸�");
	 JButton aCourse=new JButton("��ӿγ�");
	 JButton aScore=new JButton("¼��ɼ�");
	 JButton cPassword=new JButton("�޸�����");
	 
	 JLabel labOther=new JLabel("����");
	 JButton relogin=new JButton("���µ�¼");
	 JButton exit=new JButton("�˳�");

	 static String Id;
	 //����û�ID
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
	 
	   class ReLoginListener implements ActionListener{//���µ�¼
		public void actionPerformed(ActionEvent e) {
			new LoginUI().Init();
			frame.dispose();
		}
	  }
	   
       class AddCourseListener implements ActionListener{//����¿γ�
		public void actionPerformed(ActionEvent e) {
			new AddCourse().add();
			frame.dispose();
		}
		   
	   }
	   
	   class AddScoreListener implements ActionListener{//¼��ɼ�
			public void actionPerformed(ActionEvent e) {
				new AddScore().add(Id);
				frame.dispose();
			  }
			   
		   }
	 
	   class QueryTeacherListener implements ActionListener{//��ѯ��ʦ������Ϣ
		public void actionPerformed(ActionEvent e) {
			new QueryUser().QueryTeacher(Id,1);
			frame.dispose();
		  }
		   
	   }
	   
	   class QueryStudentListener implements ActionListener{//��ѯѧ����Ϣ
			public void actionPerformed(ActionEvent e) {
				new QueryUser().InUI("S");
				frame.dispose();
			}
			   
		  }
	  
	   class QueryScoreListener implements ActionListener{//�鿴�ɼ�
			public void actionPerformed(ActionEvent e) {
				new UserQueryScore().buildUI(2);
				frame.dispose();
			}
		  }
	 
	   class ExitListener implements ActionListener{// �˳�
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			} 
		 }
	   
	   class ChangerPasswordListener implements ActionListener{//�޸����������
			public void actionPerformed(ActionEvent e) {
				 new ChangePassword().ChangeUI(Id, "Teacher");
				 frame.dispose();
			}
			 
		 }
}
