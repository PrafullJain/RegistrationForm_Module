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

public class RemUser extends JFrame {
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JButton b;
	public RemUser(String rmgmailstr) {
		
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
		
		String s1=rmgmailstr;
		
		l4=new JLabel();
		l4.setBounds(200,300,200,50);
		add(l4);
		l4.setVisible(false);
		

		b=new JButton("Login");
		b.setBounds(580,400,150,30);
		add(b);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginRegF.main(new String[]{});
				dispose();
			}
		});
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "1234");
			String query="delete from regform where email=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,s1);
			int i=pst.executeUpdate();
			if(i==1)
			{
				JOptionPane.showMessageDialog(RemUser.this,"User will Deleted Successfully");
				l4.setVisible(true);
				l4.setText("User-Deleted Successfully");
				b.setEnabled(true);
			}			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		setTitle("Registration-Form");
		setLayout(null);
		setVisible(true);
		
		setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}

}
