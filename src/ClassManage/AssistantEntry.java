package ClassManage;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AssistantEntry implements ActionListener
{
	private String assistantName;
	private String aUserName;
	private String aPassword;
	private String since;
	private String companyID;
	private String assistantID;
	
	private Connection con;	
	private JFrame frame = new JFrame("Driver Entry");
	
	private JLabel aNameLabel = new JLabel("Enter The Assistant Name : ");
	private JLabel auserNameLabel = new JLabel("Enter UserName  : ");
	private JLabel aPasswordLabel = new JLabel("Enter Assistant Password : ");
	private JLabel sinceLabel = new JLabel("Enter The Join Date : ");
	private JLabel assistantIDLabel = new JLabel("Enter The Assistant ID : ");
	
	private JTextField aNameText = new JTextField();
	private JTextField auserNameText = new JTextField();
	private JTextField aPasswordText = new JTextField();
	private JTextField sinceText = new JTextField();
	private JTextField assistantIDText = new JTextField();
	
	private JButton saveAssistant = new JButton("Entry New Assistant");
	private JButton deleteAssistant = new JButton("Delete Assistant");
	private JButton  edit = new JButton("Edit The Existing Assistant Info");
	
	private JPanel upPanel = new JPanel();
	private JPanel downPanel = new JPanel();
	
	public AssistantEntry(String companyID)
	{
		this.companyID = companyID;
	}
	
	public void iniWindow()
	{
		GridLayout gl = new GridLayout(5,2,10,10);
		GridLayout gl1 = new GridLayout(2,2,10,10);
		BorderLayout frameLayout = new BorderLayout();
		
		upPanel.setLayout(gl);
		downPanel.setLayout(gl1);
		frame.setLayout(frameLayout);
		
		upPanel.add(aNameLabel);
		upPanel.add(aNameText);
		upPanel.add(auserNameLabel);
		upPanel.add(auserNameText);
		upPanel.add(aPasswordLabel);
		upPanel.add(aPasswordText);
		upPanel.add(sinceLabel);
		upPanel.add(sinceText);
		upPanel.add(saveAssistant);
		
		downPanel.add(assistantIDLabel);
		downPanel.add(assistantIDText);
		downPanel.add(deleteAssistant);
		downPanel.add(edit);
		
		frame.add(upPanel,frameLayout.NORTH);
		frame.add(downPanel, frameLayout.SOUTH);
		
		frame.setSize(750, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		saveAssistant.addActionListener(this);
		deleteAssistant.addActionListener(this);
		edit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent a)
	{
		if(a.getSource() == saveAssistant)
		{
			assistantName = aNameText.getText();
			aUserName = auserNameText.getText();
			aPassword = aPasswordText.getText();
			since = sinceText.getText();
			
			String empId=null;
			String assistantID = null;
			
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			String var = null;
			String query = null;
			
			String query2 = "select count(id) from employee ;";
			String query1 = null;
			String query3 = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				Statement st = con.createStatement();
				ResultSet rst = st.executeQuery(query2); // to find the new id for employee
				rst.next();
				empId = Integer.toString(Integer.parseInt(rst.getString(1)) + 1) ;
				
				var = empId + ",'" + assistantName + "','" + aUserName + "' ,'" + aPassword + "',0," + "'" + since + "'," + "0," + companyID;	
				query = "INSERT INTO employee(id,name,username,password,rating,since,CountPassenger,company_id) "
						+ " VALUES(" + var + ")";
				
				st.executeUpdate(query); //insert to employee
				
				ResultSet rs = st.executeQuery(query2); //count total employee to insert in driver
				rs.next();
				empId = rs.getString(1);
				
				query3 = "select count(id) from assistant ;";
				rst = st.executeQuery(query3);
				rst.next();
				assistantID = Integer.toString(Integer.parseInt(rst.getString(1)) + 1);
				
				String temp = assistantID + ", " + empId ;
				query1 = "INSERT into assistant(id,employee_id) values(" + temp + ");";
				st.executeUpdate(query1);
				
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		else if(a.getSource() == deleteAssistant)
		{
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			String empID;
			
			assistantID = assistantIDText.getText();
			//System.out.println(driverID);
			
			String query ;
			String query1 = "select employee_id from assistant where id = " + assistantID;
			String query2;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query1); //retrive driver id
				rs.next();
				empID = rs.getString(1);
				
				query = "delete from employee where id = " + empID; //delete employee from employee table
				st.executeUpdate(query);
								
				query2 = "delete from assistant where employee_id = " + empID; // delete from driver
				st.executeUpdate(query2);
				
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		else if(a.getSource() == edit)
		{
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			assistantName = aNameText.getText();
			aUserName = auserNameText.getText();
			aPassword = aPasswordText.getText();
			since = sinceText.getText();
			assistantID = assistantIDText.getText();
			
			String query = "update employee set name = '" + assistantName + "',username = '" + aUserName + "', "
					+ "password = '" + aPassword + "', since = '" + since + "' "
							+ "where id = " + assistantID;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				Statement st = con.createStatement();
				st.executeUpdate(query);
				
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}

}
