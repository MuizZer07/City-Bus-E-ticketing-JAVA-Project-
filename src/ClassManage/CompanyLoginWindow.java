package ClassManage;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CompanyLoginWindow implements ActionListener
{
	private String ownerUserName;
	private String ownerPassword;
	private String companyID;
	
	public Connection con; // mysql connection var
	
	private JFrame frame = new JFrame("Company Login Window");
	
	private JLabel companyUNameLabel = new JLabel("Enter User Name  : ");
	private JLabel companyPassLabel = new JLabel("Enter Your Password : ");
	
	private JTextField companyUNameText = new JTextField("Enter User Name Here");
	private JPasswordField companyPassText = new JPasswordField();
	
	private JButton Login = new JButton("Login");
	private JButton driver = new JButton("Company Driver");
	private JButton assistant = new JButton("Company Assistant");
	private JButton bus = new JButton("Company Bus");

	public CompanyLoginWindow(){}
	
	public void iniWindow()
	{
		GridLayout gl = new GridLayout(4,2,30,10);
		frame.setLayout(gl);
		
		frame.add(companyUNameLabel);
		frame.add(companyUNameText);
		frame.add(companyPassLabel);
		frame.add(companyPassText);
		frame.add(Login);
		frame.add(driver);
		frame.add(assistant);
		frame.add(bus);
		
		frame.setSize(800, 650);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		Login.addActionListener(this);
		driver.addActionListener(this);
		assistant.addActionListener(this);
		bus.addActionListener(this);
		
		driver.setEnabled(false);
		assistant.setEnabled(false);
		bus.setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent a) 
	{
		if(a.getSource() == Login)
		{
			ownerUserName = companyUNameText.getText();
			ownerPassword = companyPassText.getText();
			
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			String query = "select * from company ;";
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				//JOptionPane.showMessageDialog(null, "Connected to DataBase");
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				//System.out.println("--------------------");
				
				boolean key = true;
				
				while(rs.next())
				{				
					if(rs.getString(5).equals(ownerPassword) && rs.getString(4).equals(ownerUserName))
					{
						JOptionPane.showMessageDialog(null, "Login Success" + ""
								+ "\n" + "User Name " + ownerUserName + "\nName : " + rs.getString(2));
						
						companyID = rs.getString(1);
						
						key = true;
						break;
					}
					else
					{
						key = false;
					}
				}
				
				if(key == false)
					JOptionPane.showMessageDialog(null, "Login unSuccessfull");
				else
				{
					driver.setEnabled(true);
					assistant.setEnabled(true);
					bus.setEnabled(true);
				}
				
				rs.close();
				
				
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		else if(a.getSource() == driver)
		{
			DriverEntry de = new DriverEntry(companyID);
			de.iniWindow();
			frame.dispose();
		}
		else if(a.getSource() == assistant)
		{
			AssistantEntry ae = new AssistantEntry(companyID);
			ae.iniWindow();
			frame.dispose();
		}
		else if(a.getSource() == bus)
		{
			BusEntry be = new BusEntry(companyID);
			be.iniWindow();
			frame.dispose();
		}
	}

}
