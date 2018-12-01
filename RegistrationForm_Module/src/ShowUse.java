package J2EE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class ShowUse {
	JLabel l1,l2,l3;
	JPasswordField pf;
	JButton b1,b2,b3;
	JTable jt;
	String[] columnames={"Email","ID_Number","Name","Gender","Interested Programing Language"};
	String email,name,gender,idn,proglang;
	
	public ShowUse(final String a) {
		
		final JFrame f=new JFrame();
		l1=new JLabel("User-Information");
		l1.setBounds(600,18,180,80);
		l1.setBackground(Color.BLACK);
		l1.setFont(new Font("Arial",Font.BOLD,22));
		f.add(l1);
		
		l2=new JLabel("-----------");
		l2.setBounds(670,25,600,80);
		l2.setBackground(Color.BLACK);
		f.add(l2);
		
		l3=new JLabel("User-Panel ");
		l3.setBounds(530,0,347,50);
		l3.setFont(new Font("Lucida Calligraphy",Font.BOLD,30));
		l3.setForeground(Color.BLUE);
		//l3.setBorder(BorderFactory.createDashedBorder(Color.GREEN,1,1,1,true));
		f.add(l3);	
		email=a;		
		
		DefaultTableModel dtm=new DefaultTableModel();
		dtm.setColumnIdentifiers(columnames);
		dtm.addRow(columnames);

		jt=new JTable();
		jt.setModel(dtm);
		jt.setBounds(200,250,830,46);
		jt.getColumnModel().getColumn(0).setPreferredWidth(58);
		jt.getColumnModel().getColumn(4).setPreferredWidth(102);
	
		
		f.add(jt);
		int i=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/prac", "root", "1234");
			String query="select * from regform where email=?";
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,email);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				idn=rs.getString("id");
				name=rs.getString("name");
				gender=rs.getString("gender");
				proglang=rs.getString("Programing_lang");
				dtm.addRow(new Object[]{email,idn,name,gender,proglang});
				i++;
			}		
			if(i<1)
			{
				JOptionPane.showMessageDialog(null,"No Records Found","Error",JOptionPane.ERROR_MESSAGE);
			}
			if(i==1)
			{
				System.out.println(i+" Record Found");
			}
			else
			{
				System.out.println(i+" Records Found");
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
			b1=new JButton("Edit");
			b1.setBounds(700,550,60,30);
			f.add(b1);
			b1.addActionListener(new ActionListener() {
				String em=a;
				@Override
				public void actionPerformed(ActionEvent e) {
					new EditUser(em);
					f.dispose();
				}
			});

			b2=new JButton("Remove");
			b2.setBounds(780,550,80,30);
			f.add(b2);
			b2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String rmgmailstr=a;
					new RemUser(rmgmailstr);
					f.dispose();
				}
			});
			b3=new JButton("Sign-Out");
			b3.setBounds(440,550,140,30);
			f.add(b3);
			b3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					LoginRegF.main(new String[]{});
					f.dispose();
				}
			});
			
		f.setTitle("Registration-Form");
		f.setLayout(null);
		f.setVisible(true);
		f.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}