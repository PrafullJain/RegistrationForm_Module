package J2EE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.tools.Tool;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegistrationForm extends JFrame{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
	JTextField t1,t2;
	
	JPasswordField p1,p2;
	JButton b1,b2;

	RegistrationForm()
	{
/*
		ImageIcon ic=new ImageIcon("src/J2EE/serv-register.jpg");
		Image img=ic.getImage();
		Image img_temp=img.getScaledInstance((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(),Image.SCALE_SMOOTH);
		ic=new ImageIcon(img_temp);
		JLabel background=new JLabel("",ic,JLabel.CENTER);
		background.setBounds(0,0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		background.setForeground(new Color(0,0,0,200));
		add(background);*/

		
		l7=new JLabel("Sign-Up");
		l7.setBounds(650,18,100,80);
		l7.setBackground(Color.BLACK);
		l7.setFont(new Font("Arial",Font.BOLD,22));
		add(l7);
		
		l8=new JLabel("-----------");
		l8.setBounds(670,25,600,80);
		l8.setBackground(Color.BLACK);

		add(l8);

		l9=new JLabel("Interested Programming Language: ");
		l9.setBounds(50,420,250,80);
		l9.setBackground(Color.CYAN);
		add(l9);

		String[] lang={"C","C++","C#","Lua","PHP","Java"};
		final JComboBox cb=new JComboBox(lang);
		cb.setBounds(280,450,80,30);
		add(cb);
		
		l10=new JLabel("Gender: ");
		l10.setBounds(50,500,200,80);
		add(l10);
		
		final JRadioButton r1=new JRadioButton("Male");
		r1.setBounds(100,515,60,50);
		add(r1);

		final JRadioButton r2=new JRadioButton("Female");
		r2.setBounds(160,515,70,50);
		add(r2);
		
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);bg.add(r2);

		
		l1=new JLabel("Registration-Form ");
		l1.setBounds(530,0,347,50);
		l1.setFont(new Font("Lucida Calligraphy",Font.BOLD,30));
		l1.setForeground(Color.BLUE);
		//l1.setBorder(BorderFactory.createDashedBorder(Color.GREEN,1,1,1,true));
		add(l1);
		
		l2=new JLabel("User-Name: ");
		l2.setBounds(50,100,100,50);
		add(l2);
		
		t1=new JTextField();
		t1.setBounds(160,110,130,30);
		add(t1);
		
		l3=new JLabel("E-Mail: ");
		l3.setBounds(50,170,80,50);
		add(l3);
		l3.setToolTipText("xyz@domain.com");
		
		t2=new JTextField();
		t2.setBounds(160,190,160,30);
		add(t2);
		t2.setToolTipText("xyz@domain.com");

		
		l4=new JLabel("Password: ");
		l4.setBounds(50,260,80,50);
		add(l4);
		
		p1=new JPasswordField();
		p1.setBounds(160,270,130,30);
		add(p1);

		l5=new JLabel("Confirm-Password: ");
		l5.setBounds(50,340,160,50);
		add(l5);
		

		p2=new JPasswordField();
		p2.setBounds(190,350,130,30);
		add(p2);
		
	
		b1=new JButton("Submit");
		b1.setBounds(660,640,100,30);
		add(b1);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Getting all the Informations
				String name=t1.getText();
				String em=t2.getText();
				String passw=p1.getText();
				String conpass=p2.getText();
				String inprog=(String) cb.getSelectedItem();
				String m=null;
				if(r1.isSelected()){
					m=r1.getLabel();
				}else
				{
					m=r2.getLabel();
				}
			
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "1234");
					/*String query="insert into regform(name,password,email) values('"+name+"','"+passw+"','"+em+"')";
					Statement stmt=con.createStatement();
					int i=stmt.executeUpdate(query);
						
					if(i==1)
					{
						JOptionPane.showMessageDialog(RegistrationForm.this,"Yahh..Data Inserted Successfully","Confirmation-Message",JOptionPane.PLAIN_MESSAGE);
						System.out.println("Data successfully inserted");
					}
					else
					{
						System.out.println("Data insertion failed");
						JOptionPane.showMessageDialog(RegistrationForm.this,"Sorry..Data Isn't inserted Successfully","Confirmation-Message",JOptionPane.PLAIN_MESSAGE);
					}*/
					if(passw.equalsIgnoreCase(conpass) && !t2.getText().equalsIgnoreCase("")&& !p1.getText().equalsIgnoreCase("") && !p2.getText().equalsIgnoreCase(""))
					{
					String query="insert into regform(name,password,email,Programing_lang,Gender)values(?,?,?,?,?)";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1,name);
					pst.setString(2,passw);
					pst.setString(3,em);
					pst.setString(4,inprog);
					pst.setString(5,m);
					
					int i=pst.executeUpdate();
					
					if(i==1)
					{
						JOptionPane.showMessageDialog(RegistrationForm.this,"Yahh..Data Inserted Successfully","Confirmation-Message",JOptionPane.PLAIN_MESSAGE);
						System.out.println("Data successfully inserted");
						LoginRegF.main(new String[] {});
						dispose();
					}
					else
					{
						System.out.println("Data insertion failed");
						JOptionPane.showMessageDialog(RegistrationForm.this,"Sorry..Data Isn't inserted Successfully","Confirmation-Message",JOptionPane.PLAIN_MESSAGE);
					}
				}
					else
					{
						JOptionPane.showMessageDialog(RegistrationForm.this,"Please Properly entered the Email ,Password as well as Confirmation-Password","Confirmation-Message",JOptionPane.PLAIN_MESSAGE);
					}
				}catch(Exception ex){ex.printStackTrace();}
			}
		});
		
		b2=new JButton("Login");
		b2.setBounds(1280,10,70,20);
		add(b2);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			 LoginRegF.main(new String[]{});
			 dispose();
			}
		});
		
		setTitle("Registration-Form");
		setLayout(null);
		setVisible(true);
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new RegistrationForm();
		
	}
}
