package com.pqy.management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginUI {
	JFrame frame=new JFrame("ѧ����Ϣ����ϵͳ");
	 JRadioButton rb1=new JRadioButton("����Ա");
	 JRadioButton rb2=new JRadioButton("ѧ��");
	 JRadioButton rb3=new JRadioButton("��ʦ");
	 JButton btn1=new JButton("��¼");
	 JTextField Id=new JTextField(20);
	 JTextField Password=new JTextField(20);
	 JLabel labId=new JLabel("�û���ID"); 
	 JLabel labPassword=new JLabel("����"); 
	 ButtonGroup bg=new ButtonGroup(); 
	 Container con=frame.getContentPane(); 
	 //��ʼ����¼���� 
	 public void Init() {  
		 frame.setSize(350,400);
		 frame.setLocation(700,450);
		 frame.setLayout(null);//�ղ���
		 ImageIcon im=new ImageIcon("D:\\ͼƬ\\��ֽ\\1592290287459.jpeg");//���õ�¼����
		 JLabel pa=new JLabel(im); 
		 pa.setBounds(0, 0, 350, 400); 
		 bg.add(rb1);
		 bg.add(rb2); 
		 bg.add(rb3);
		 rb1.setBounds(60, 60 ,70 ,30 );
		 rb2.setBounds(130, 60, 70, 30);
		 rb3.setBounds(200, 60, 70, 30);
		/* rb1.setOpaque(false);
		 rb2.setOpaque(false);
		 rb3.setOpaque(false);*/
		 con.add(rb1);
		 con.add(rb2); 
		 con.add(rb3);
		 labId.setBounds(43, 125, 75, 30);
		 labPassword.setBounds(67, 175, 75, 30);
		 Id.setBounds(100, 125, 125, 25);
		 Password.setBounds(100, 175, 125, 25);
		 btn1.setBounds(130, 250, 60, 30);
		 con.add(labId);
		 con.add(Id);
		 con.add(labPassword); 
		 con.add(Password);
		 con.add(btn1);
		 con.add(pa);
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 MyActionListener al=new MyActionListener(); 
		 
		 btn1.addActionListener(al);
	 }
	  
	 public class MyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String user=Id.getText();
			String word=Password.getText();
			boolean b;
			Login login=new Login();
			if(rb1.isSelected()) {
				b=login.isManager(user, word);
				if(b) { 
					ManagerMenu m=new ManagerMenu();
					m.User(user);
					m.buildMenu(); 
					frame.dispose(); 
				} 
				else {
					JOptionPane.showMessageDialog(null, "�û���ID�������������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				} 
				  
			}  
			
			else if(rb2.isSelected()){
				b=login.isStudent(user, word);
               if(b) {
            	   StudentMenu m=new StudentMenu();
					m.User(user);
					m.buildMenu(); 
					frame.dispose(); 
				}
				else {
					JOptionPane.showMessageDialog(null, "�û���ID�������������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
			else if(rb3.isSelected()) {
				b=login.isTeacher(user, word);
               if(b) {
            	   TeacherMenu t=new TeacherMenu();
					t.User(user);
					t.buildMenu();
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "�û���ID�������������������","��ʾ",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
		} 
	 }


}
