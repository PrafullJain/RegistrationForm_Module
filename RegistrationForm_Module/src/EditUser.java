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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EditUser extends JFrame{
JLabel l1,l2,l3;
JTextField tf1,tf2;
JButton b1,b2,b3,b4,b5,b6;

	public EditUser(final String x) {
		
		l1=new JLabel("Edit User-Profile");
		l1.setBounds(670,18,260,80);
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
		
		tf1=new JTextField();
		tf1.setBounds(60,260,150,30);
		tf1.setVisible(false);
		add(tf1);
		
		
		b1=new JButton("Name");
		b1.setBounds(80,200,80,30);
		add(b1);
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf1.setVisible(true);
			}
		});
		
		tf2=new JTextField();
		tf2.setBounds(230,260,150,30);
		tf2.setVisible(false);
		add(tf2);
	
		b2=new JButton("Email");
		b2.setBounds(250,200,80,30);
		add(b2);
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf2.setVisible(true);
			}
		});

		final JRadioButton r1=new JRadioButton("Male");
		r1.setBounds(420,250,60,50);
		r1.setVisible(false);
		add(r1);

		final JRadioButton r2=new JRadioButton("Female");
		r2.setBounds(490,250,70,50);
		r2.setVisible(false);
		add(r2);

		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);bg.add(r2);
		
		b3=new JButton("Gender");
		b3.setBounds(430,200,140,30);
		add(b3);
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				r1.setVisible(true);
				r2.setVisible(true);
			}
		});
		
		String[] lang={"C","C++","C#","Lua","PHP","Java"};
		final JComboBox cb=new JComboBox(lang);
		cb.setBounds(680,250,80,30);
		cb.setVisible(false);
		add(cb);
		
		b4=new JButton("Interested-Progrming Language");
		b4.setBounds(640,200,250,30);
		add(b4);
		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cb.setVisible(true);
			}
		});
		b5=new JButton("Update");
		b5.setBounds(550,350,80,30);
		add(b5);
		b5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String prevemail=x;
				String name=tf1.getText();
				String email=tf2.getText();
				String gender=null;
				String prog=(String)cb.getSelectedItem();
				if(r1.isSelected()){
					gender=r1.getLabel();
				}else
				{
					gender=r2.getLabel();
				}
				String a=tf2.getText();
				if(!tf2.getText().equalsIgnoreCase(""))
				{
					try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "1234");
					String query="update regform set email=?,name=?,gender=?,Programing_lang=? where email=?";
					PreparedStatement pst=con.prepareStatement(query);

					pst.setString(1,email);
					pst.setString(2,name);
					pst.setString(3,gender);
					pst.setString(4,prog);
					pst.setString(5,prevemail);
					
					int i=pst.executeUpdate();
					if(i==1)
					{
						JOptionPane.showMessageDialog(EditUser.this,"You're Profile is Updated Successfully");
						new ShowUse(a);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(EditUser.this,"You're Profile isn't  Updated Successfully");
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				}else
				{
					JOptionPane.showMessageDialog(EditUser.this,"Please Enter Your Email-Address");
				}
				
			}
		});
		b6=new JButton("Sign-Out");
		b6.setBounds(680,350,140,30);
		add(b6);
		b6.addActionListener(new ActionListener() {
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
}
