package ClassManage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BusEntry implements ActionListener
{
	private String companyID = null;
	private String licensePlateNo;
	private String seatCapacity;
	private String routeID;
	private String driverID;
	private String assistantID;
	private String available;
	private String busID; // to delete the bus
	
	private Connection con;
	
	private JFrame frame = new JFrame("Bus Entry");
	
	private Font font = new Font("Serif",Font.BOLD,18);
	
	private JLabel licenseLabel = new JLabel("Enter The License No : ");
	private JLabel seatLabel = new JLabel("Enter The Seat No : ");
	private JLabel routeLabel = new JLabel("Enter The Route No : ");
	private JLabel driverIDLabel = new JLabel("Enter The Driver ID : ");
	private JLabel assistantIDLabel = new JLabel("Enter The Assistant ID : ");
	private JLabel availableLabel = new JLabel("Enter The Availablity : ");
	private JLabel busIDLabel = new JLabel("Enter Bus ID");
	private JLabel editLabel = new JLabel("To Edit, Enter Bus ID and Above info");
	
	private JTextField licenseText = new JTextField("Enter License No Here");
	private JTextField seatText = new JTextField("Enter Seat No Here");
	private JTextField routeText = new JTextField("Enter Route No Here");
	private JTextField driverIDText = new JTextField("Enter Driver ID Here");
	private JTextField assistantIDText = new JTextField("Enter Assistant ID Here");
	private JTextField availableText = new JTextField("Enter Availability Here");
	private JTextField busIDText = new JTextField("Enter Bus ID Here");
	
	private JButton saveBus = new JButton("Entry New Bus");
	private JButton deleteBus = new JButton("Delete Bus");
	private JButton  checkHistory = new JButton("Check History");
	private JButton  clear = new JButton("Clear");
	private JButton  edit = new JButton("Edit The Existing Info");
	
	private JPanel upPanel = new JPanel();
	private JPanel downPanel = new JPanel();
	
	private Calendar calendar = Calendar.getInstance();
	
	public BusEntry(String companyID)
	{
		this.companyID = companyID;
	}
	
	public void iniWindow()
	{
		GridLayout gl = new GridLayout(7,2,10,10);
		GridLayout gl1 = new GridLayout(3,2,10,10);
		BorderLayout frameLayout = new BorderLayout();
		
		upPanel.setLayout(gl);
		downPanel.setLayout(gl1);
		frame.setLayout(frameLayout);
		
		upPanel.add(licenseLabel);
		upPanel.add(licenseText);
		upPanel.add(seatLabel);
		upPanel.add(seatText);
		upPanel.add(routeLabel);
		upPanel.add(routeText);
		upPanel.add(driverIDLabel);
		upPanel.add(driverIDText);
		upPanel.add(assistantIDLabel);
		upPanel.add(assistantIDText);
		upPanel.add(availableLabel);
		upPanel.add(availableText);
		upPanel.add(saveBus);
		upPanel.add(clear);
		
		downPanel.add(busIDLabel);
		downPanel.add(busIDText);
		downPanel.add(deleteBus);
		downPanel.add(checkHistory);
		downPanel.add(editLabel);
		downPanel.add(edit);
		
		frame.add(upPanel,frameLayout.NORTH);
		frame.add(downPanel, frameLayout.SOUTH);
		
		frame.setSize(750, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		saveBus.addActionListener(this);
		deleteBus.addActionListener(this);
		checkHistory.addActionListener(this);
		clear.addActionListener(this);
		edit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == saveBus)
		{
			licensePlateNo = licenseText.getText();
			seatCapacity = seatText.getText();
			routeID = routeText.getText();
			driverID = driverIDText.getText();
			assistantID = assistantIDText.getText();
			available = availableText.getText();
			
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			String newBusID = null;
			
			String var;
			String query;
			String query1 = "select count(id) from bus ;";
			String query2 = "insert into bus_history(date,route_id,total_trips,total_passenger_count)"
					+ " values('" + calendar.getTime() + "', " + routeID + ", 0 , 0 ;";
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				Statement st = con.createStatement();
				
				ResultSet rs = st.executeQuery(query1);
				rs.next();
				newBusID = Integer.toString(Integer.parseInt(rs.getString(1)) + 1); 
								
				var = newBusID + ", "+ companyID + ",'" + licensePlateNo + "' ," + seatCapacity + ",0" + " , 0 ," + routeID + ", 'null', 'null',"
						+ " " + driverID + ", " + assistantID + ", '0', '1' ";	
				query = "INSERT INTO bus(id,company_id,Licence_plate_no,seat_capacity,seat_taken,passenger_count,route_id,bus_status,current_location,driver_id,assistant_id,trip_count_per_day,available) "
						+ " VALUES(" + var + ")";
				
				st.executeUpdate(query);
				st.executeUpdate(query2); // update bus history
				
				con.close();
			}
			catch(Exception ex)
			{
				System.out.println(ex.toString());
			}
		}
		else if(e.getSource() == clear)
		{
			licenseText.setText(null);
			routeText.setText(null);
			seatText.setText(null);
			driverIDText.setText(null);
			assistantIDText.setText(null);
			availableText.setText(null);
			busIDText.setText(null);
		}
		else if(e.getSource() == deleteBus)
		{
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			busID = busIDText.getText();
			
			String query = "delete from bus where id = " + busID;
			
			System.out.println(busID);
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
		else if(e.getSource() == checkHistory)
		{
			String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			busID = busIDText.getText();
			
			String query = "select date, route_id, total_trips, total_passenger_count from bus_history "
					+ "where bus_id = " + busID;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				rs.next();
				
				JOptionPane.showMessageDialog(null, "Data : " + rs.getString(1) + "\nroute id : " + rs.getString(2) + "\ntotal trip : " + rs.getString(3) + "\ntotal passenger count : " + rs.getString(4));
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
			
			licensePlateNo = licenseText.getText();
			seatCapacity = seatText.getText();
			routeID = routeText.getText();
			driverID = driverIDText.getText();
			assistantID = assistantIDText.getText();
			available = availableText.getText();
			busID = busIDText.getText();
			
			String query = "update bus set Licence_plate_no = '" + licensePlateNo + "',seat_capacity = " + seatCapacity + ", "
					+ "route_id = " + routeID + ", driver_id = " + driverID + ", assistant_id = " + assistantID + ", available = " + available + " "
							+ "where id = " + busID;
			
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
