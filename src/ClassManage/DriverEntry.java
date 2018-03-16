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

public class DriverEntry implements ActionListener
{
	private String driverName;
	private String dUserName;
	private String dPassword;
	private String since;
	private String companyID;
	private String licenseNo;
	private String driverID;
	
	private Connection con;
	
	private JFrame frame = new JFrame("Driver Entry");
		
	private JLabel dNameLabel = new JLabel("Enter The Driver Name : ");
	private JLabel duserNameLabel = new JLabel("Enter UserName  : ");
	private JLabel dPasswordLabel = new JLabel("Enter Driver Password : ");
	private JLabel sinceLabel = new JLabel("Enter The Join Date : ");
	private JLabel licenceLabel = new JLabel("Enter The Licence No : ");
	private JLabel driverIDLabel = new JLabel("Enter The Driver ID : ");
	
	private JTextField dNameText = new JTextField();
	private JTextField duserNameText = new JTextField();
	private JTextField dPasswordText = new JTextField();
	private JTextField sinceText = new JTextField();
	private JTextField licenceText = new JTextField();
	private JTextField driverIDText = new JTextField();
	
	private JButton saveDriver = new JButton("Entry New Driver");
	private JButton deleteDriver = new JButton("Delete Driver");
	private JButton  edit = new JButton("Edit The Existing Driver Info");
	
	private JPanel upPanel = new JPanel();
	private JPanel downPanel = new JPanel();
	
	public DriverEntry(String companyID)
	{
		this.companyID = companyID;
	}
	
	public void iniWindow()
	{
		GridLayout gl = new GridLayout(6,2,10,10);
		GridLayout gl1 = new GridLayout(2,2,10,10);
		BorderLayout frameLayout = new BorderLayout();
		
		upPanel.setLayout(gl);
		downPanel.setLayout(gl1);
		frame.setLayout(frameLayout);
		
		upPanel.add(dNameLabel);
		upPanel.add(dNameText);
		upPanel.add(duserNameLabel);
		upPanel.add(duserNameText);
		upPanel.add(dPasswordLabel);
		upPanel.add(dPasswordText);
		upPanel.add(sinceLabel);
		upPanel.add(sinceText);
		upPanel.add(licenceLabel);
		upPanel.add(licenceText);
		upPanel.add(saveDriver);
		
		downPanel.add(driverIDLabel);
		downPanel.add(driverIDText);
		downPanel.add(deleteDriver);
		downPanel.add(edit);
		
		frame.add(upPanel,frameLayout.NORTH);
		frame.add(downPanel, frameLayout.SOUTH);
		
		frame.setSize(750, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		saveDriver.addActionListener(this);
		deleteDriver.addActionListener(this);
		edit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == saveDriver)
		{
			driverName = dNameText.getText();
			dUserName = duserNameText.getText();
			dPassword = dPasswordText.getText();
			since = sinceText.getText();
			licenseNo = licenceText.getText();
			String empId=null;
			String driverID = null;
			
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
				
				var = empId + ",'" + driverName + "','" + dUserName + "' ,'" + dPassword + "',0," + "'" + since + "'," + "0," + companyID;	
				query = "INSERT INTO employee(id,name,username,password,rating,since,CountPassenger,company_id) "
						+ " VALUES(" + var + ")";
				
				st.executeUpdate(query); //insert to employee
				
				ResultSet rs = st.executeQuery(query2); //count total employee to insert in driver
				rs.next();
				empId = rs.getString(1);
				
				query3 = "select count(id) from driver ;";
				rst = st.executeQuery(query3);
				rst.next();
				driverID = Integer.toString(Integer.parseInt(rst.getString(1)) + 1);
				
				String temp = driverID + ", " + empId + ",'" + licenseNo + "'";
				query1 = "INSERT into driver(id,employee_id,Licence_no) values(" + temp + ");";
				st.executeUpdate(query1);
				
				con.close();
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		}
		else if(e.getSource() == deleteDriver)
		{
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			String empID;
			
			driverID = driverIDText.getText();
			//System.out.println(driverID);
			
			String query ;
			String query1 = "select employee_id from driver where id = " + driverID;
			String query2;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query1); //retrive driver id
				rs.next();
				empID = rs.getString(1);
				
				//System.out.println(empID);
				query = "delete from employee where id = " + empID; //delete employee from employee table
				st.executeUpdate(query);
								
				query2 = "delete from driver where employee_id = " + empID; // delete from driver
				st.executeUpdate(query2);
				
				con.close();
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		}
		else if(e.getSource() == edit)
		{
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			driverName = dNameText.getText();
			dUserName = duserNameText.getText();
			dPassword = dPasswordText.getText();
			since = sinceText.getText();
			licenseNo = licenceText.getText();
			driverID = driverIDText.getText();
			
			String query = "update employee set name = '" + driverName + "',username = '" + dUserName + "', "
					+ "password = '" + dPassword + "', since = '" + since + "' "
							+ "where id = " + driverID;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				Statement st = con.createStatement();
				st.executeUpdate(query);
				
				con.close();
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
			
		}
	}

}
