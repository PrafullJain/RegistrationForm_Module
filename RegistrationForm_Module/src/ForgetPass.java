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

public class ForgetPass extends JFrame {
	JLabel l1,l2,l3,l4,l5;
	JPasswordField pf1,pf2;
	JButton b1,b2;
	
	public ForgetPass(final String fgpas) {
		l1=new JLabel("Registration Form");
		l1.setBounds(600,18,250,80);
		l1.setBackground(Color.BLACK);
		l1.setFont(new Font("Arial",Font.BOLD,22));
		add(l1);
		
		l2=new JLabel("-----------");
		l2.setBounds(670,25,600,80);
		l2.setBackground(Color.BLACK);
		add(l2);
		
		l3=new JLabel("Forget-Password");
		l3.setBounds(530,0,347,50);
		l3.setFont(new Font("Lucida Calligraphy",Font.BOLD,30));
		l3.setForeground(Color.BLUE);
		//l3.setBorder(BorderFactory.createDashedBorder(Color.GREEN,1,1,1,true));
		add(l3);
		
		
		
		l4=new JLabel("New-Password: ");
		l4.setBounds(380,200,100,20);
		add(l4);
		
		
		pf1=new JPasswordField();
		pf1.setBounds(520,200,150,30);
		add(pf1);
		
		l5=new JLabel("Confirm-Password: ");
		l5.setBounds(380,280,140,20);
		add(l5);
		
		pf2=new JPasswordField();
		pf2.setBounds(520,280,150,30);
		add(pf2);
		
		b1=new JButton("Submit");
		b1.setBounds(520,400,150,30);
		add(b1);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int s1=Integer.valueOf(pf1.getText());
				String s2=fgpas;
				String newpassw=pf1.getText();
				String connewpassw=pf2.getText();
				
				try{
					if(newpassw.equals(connewpassw))
					{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "1234");
					String query="update regform set password=? where email=?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setInt(1,s1);
					pst.setString(2,s2);
					int i=pst.executeUpdate();
					if(i==1)
					{
						JOptionPane.showMessageDialog(ForgetPass.this,"Password is Successfully Changed");
					}
					else
					{
						JOptionPane.showMessageDialog(ForgetPass.this,"Please check Your Email-Address");
					}
					}else
					{
						JOptionPane.showMessageDialog(ForgetPass.this,"Please enter correct Confirmation-Password");
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}		
			}
		});
		
		b2=new JButton("Login");
		b2.setBounds(720,400,150,30);
		add(b2);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginRegF.main(new String[] {});
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
}