package com.pqy.management;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerMenu {
	 JFrame frame=new JFrame("����Ա����");
	 JLabel labQuery=new JLabel("��ѯ");
	 JButton qStudent=new JButton("�鿴ѧ����Ϣ");
	 JButton qTeacher=new JButton("�鿴��ְ����Ϣ");
	 JButton qScore=new JButton("�鿴�ɼ�");
	 
	 JLabel labChange=new JLabel("�޸�");
	 JButton aStudent=new JButton("���ѧ����Ϣ");
	 JButton aTeacher=new JButton("��ӽ�ְ����Ϣ");
	 JButton aScore=new JButton("�����ɼ�");
	 JButton aCourse=new JButton("�����γ���Ϣ");
	 
	 JLabel labOther=new JLabel("����");
	 JButton cPassword=new JButton("�޸�����");
	 JButton exit=new JButton("�˳�");
	 String Id;
	 //����û�ID
	 public void User(String id) {
		 Id=new String(id);	 
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
	 
	 class ExitListener implements ActionListener{// �˳�
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		} 
	 }
	 
     class ChangerPasswordListener implements ActionListener{//�޸����������
		public void actionPerformed(ActionEvent e) {
			 new ChangePassword().ChangeUI(Id, "Manager");
			 frame.dispose();
		}
		 
	 }
	 
	 class AddCourseActionListener implements ActionListener{//�����γ���Ϣ������
	     public void actionPerformed(ActionEvent e) {
	    	new PublishCourse().buildUI();
	    	frame.dispose();
	     }
	}
	 
	 class AddScoreActionListener implements ActionListener{//�����ɼ�������
	     public void actionPerformed(ActionEvent e) {
	         new PublishScore().buildUI();;  	
	    	 frame.dispose();
	     }
	}
	 
	 class AddTeacherActionListener implements ActionListener{//��ӽ�ʦ��Ϣ������
	     public void actionPerformed(ActionEvent e) {
	    	 new AddTeacher().add();
	    	 frame.dispose();
	     }
	}
	 
	 class AddStudentActionListener implements ActionListener{//���ѧ����Ϣ������
	     public void actionPerformed(ActionEvent e) {
	    	 new AddStudent().add();
	    	 frame.dispose(); 
	    	 
	     }
	}
	 
	 class QueryTeacherActionListener implements ActionListener{// ��ѯ��ʦ��Ϣ������
		public void actionPerformed(ActionEvent e) {
			new QueryTeacher().QueryTeacherUI();
			frame.dispose();
		}
	 } 
	 
	 class QueryStudentActionListener implements ActionListener{// ��ѯѧ����Ϣ������
		public void actionPerformed(ActionEvent e) {
			new QueryStudent().QueryStudentUI();
			frame.dispose();
			}  
			 
		 }
	 
	 class QueryScoreActionListener implements ActionListener{// ��ѯ�ɼ�������
		public void actionPerformed(ActionEvent e) {
			new UserQueryScore().buildUI(1);
			frame.dispose();
			
			}
		 }
	
	 /*public static void main(String[] args) {
		 new ManagerMenu().buildMenu(); 
	 }*/

}
