package J2EE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginRegF extends JFrame{
	JLabel l1,l2,l3,l4,l5;
	static JTextField tf1;
	static JPasswordField pf;
	JButton b1,b2,b3;
	public LoginRegF() {
		l1=new JLabel("Sign-In");
		l1.setBounds(670,18,100,80);
		l1.setBackground(Color.BLACK);
		l1.setFont(new Font("Arial",Font.BOLD,22));
		add(l1);
		
		l2=new JLabel("-----------");
		l2.setBounds(670,25,600,80);
		l2.setBackground(Color.BLACK);
		add(l2);
		
		l3=new JLabel("Registration-Form ");
		l3.setBounds(530,0,347,50);
		l3.setFont(new Font("Lucida Calligraphy",Font.BOLD,30));
		l3.setForeground(Color.BLUE);
		//l3.setBorder(BorderFactory.createDashedBorder(Color.GREEN,1,1,1,true));
		add(l3);
		
		
		
		l4=new JLabel("Email-Address");
		l4.setBounds(480,200,140,20);
		add(l4);
		
		tf1=new JTextField("prafulljaincp@gmail.com");
		tf1.setBounds(580,200,160,30);
		add(tf1);
		
		l5=new JLabel("Password..? ");
		l5.setBounds(480,280,140,20);
		add(l5);
		
		pf=new JPasswordField("1678");
		pf.setBounds(580,280,160,30);
		add(pf);
		
		b1=new JButton("Create New Account");
		b1.setBounds(490,360,150,30);
		add(b1);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			RegistrationForm.main(new String[]{});	
			dispose();
			}
		});
		
		b2=new JButton("Forgot Password ???");
		b2.setBounds(690,360,160,30);
		add(b2);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tf1.getText().equals("")){
				String s3=tf1.getText();
				new ForgetPass(s3);
				dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(LoginRegF.this,"Please Enter the Email-Address for Forget your Password..??");
				}
			}
		});
		
		b3=new JButton("Login");
		b3.setBounds(590,440,150,30);
		add(b3);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			String email=tf1.getText();
			String passw=pf.getText();
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "1234");
			
				String query1="select * from regform where email=? and password=?";
				PreparedStatement pst=con.prepareStatement(query1);
				pst.setString(1,email);
				pst.setString(2,passw);
				ResultSet rs=pst.executeQuery();
				if(rs.next())
				{
					JOptionPane.showMessageDialog(LoginRegF.this,"You're Login Successfully");
					new ShowUse(email);
					dispose();
				}
				else
				{
						JOptionPane.showMessageDialog(LoginRegF.this,"Check Your Email & Password");
				}
			}catch(Exception ex){ex.printStackTrace();}				
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
			new LoginRegF();
	}
}
