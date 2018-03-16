/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassManage;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Muiz
 */
public class searchBuses2 extends javax.swing.JFrame {

        private String companyname;
        private String userName;
	private String ID;
	private String CurrentLocation;
	private String Destination;
	private String routeNumber;
	private float totalDistance = 0;
	private String busStatus;
	private int currLocIndex = -1; //user current location index
	
	private String busID;
	private String busLicenseID;
	private String totalSeat;
	private int emptySeat;
	private String busLocation;
	private String Fare;
        private String buses = "";
        
        	Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        DateFormat dateFormat2 = new SimpleDateFormat("hh:mm:ss");  
        String strDate = dateFormat.format(date);
        String strtime = dateFormat2.format(date);

	private LinkedList<Integer> busIndex = new LinkedList<Integer>();
	private LinkedList<String> busIDIndex = new LinkedList<String>();
	
	String[] stoppages = null; //string array
	//String[] reverseStoppages = null;
	private LinkedList<String> reverseStoppages = new LinkedList<String>();
	
	
	private Connection con; // mysql connection var
    public searchBuses2(String userName,String ID,String CurrentLocation,String Destination,String routeNumber,float totalDistance
			,String busStatus,int currLocIndex,String[] stoppages) {
                
                this.userName = userName;
		this.ID = ID; 
		this.CurrentLocation = CurrentLocation;
		this.Destination = Destination;
		this.routeNumber = routeNumber;
		this.totalDistance = totalDistance;
		this.busStatus = busStatus;
		this.currLocIndex = currLocIndex;
		this.stoppages = stoppages;
                getConnection();
                initComponents();
                setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/photo/bus-icon-17.png")));
    }

    
    public float getFare(String BusID,float totalDistance)
	{
		String url = "jdbc:mysql://localhost/citybusmgnt";
		String userName = "root";
		String pass = "";
		
		String query = "SELECT b.id,c.Fare_per_km FROM bus b "
				+ "right join company c on b.company_id = c.id;";
		
		float fare=0;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url,userName,pass);
			//JOptionPane.showMessageDialog(null, "Connected to DataBase");
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next())
			{
				if(rs.getString(1).equals(busID))
				{
					fare = rs.getFloat(2);
				}
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		fare = fare * totalDistance;
		
		return fare;
		
	}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username_txt = new javax.swing.JLabel();
        logout_btn = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        buses_txt = new javax.swing.JTextArea();
        busID_txt = new javax.swing.JTextField();
        SelectBus_btn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        AnotherBus_btn = new javax.swing.JButton();
        driver_btn = new javax.swing.JButton();
        assistant_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search Buses");

        jPanel1.setBackground(new java.awt.Color(102, 0, 153));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photo/profile-icons--download-1-130-free-profile-icon-page-1--7.png"))); // NOI18N
        jLabel4.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Hello,");

        username_txt.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        username_txt.setForeground(new java.awt.Color(204, 0, 0));
        username_txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username_txt.setText(userName+"!");

        logout_btn.setText("Logout");
        logout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_btnActionPerformed(evt);
            }
        });

        back_btn.setText("Back");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        jButton2.setText("See Trip History");

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(102, 0, 153));
        jTextField1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Current Location:");
        jTextField1.setBorder(null);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(102, 0, 153));
        jTextField2.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("Destination:");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(102, 0, 153));
        jTextField3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField3.setText(CurrentLocation);
        jTextField3.setBorder(null);

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(102, 0, 153));
        jTextField4.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(255, 255, 255));
        jTextField4.setText(Destination);
        jTextField4.setBorder(null);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(102, 0, 153));
        jTextField5.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(255, 255, 255));
        jTextField5.setText("Distance:");
        jTextField5.setBorder(null);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(102, 0, 153));
        jTextField6.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(255, 255, 255));
        jTextField6.setText(Float.toString(totalDistance)+ " km");
        jTextField6.setBorder(null);

        jLabel3.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Date: " + strDate);

        jLabel8.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Login time: "+strtime);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(logout_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                    .addGap(23, 23, 23))
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addGap(133, 133, 133)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(username_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(11, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(username_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logout_btn)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(219, 219, 219)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel8)
                    .addContainerGap(220, Short.MAX_VALUE)))
        );

        buses_txt.setColumns(20);
        buses_txt.setRows(5);
        buses_txt.setText(buses);
        buses_txt.setBorder(null);
        jScrollPane1.setViewportView(buses_txt);

        busID_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                busID_txtActionPerformed(evt);
            }
        });

        SelectBus_btn.setText("Select Bus");
        SelectBus_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectBus_btnActionPerformed(evt);
            }
        });

        jLabel1.setText("Bus ID:");

        AnotherBus_btn.setText("Get Another Bus");
        AnotherBus_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnotherBus_btnActionPerformed(evt);
            }
        });

        driver_btn.setText("Driver's Info");
        driver_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_btnActionPerformed(evt);
            }
        });

        assistant_btn.setText("Assistant's Info");
        assistant_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assistant_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(busID_txt))
                    .addComponent(AnotherBus_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driver_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(assistant_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectBus_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(busID_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SelectBus_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(driver_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assistant_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AnotherBus_btn)
                .addContainerGap(338, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        super.dispose();
        srcDesDecide2 lw = new srcDesDecide2(userName,ID);
        lw.setLocationRelativeTo(null);
        lw.setVisible(true);
    }//GEN-LAST:event_back_btnActionPerformed

    private void logout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_btnActionPerformed
        super.dispose();
        LoginWindow2 lw = new LoginWindow2();
        lw.setLocationRelativeTo(null);
        lw.setVisible(true);
    }//GEN-LAST:event_logout_btnActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void busID_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_busID_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_busID_txtActionPerformed

    
    public void updateLocation()
	{
		String url = "jdbc:mysql://localhost/citybusmgnt";
		String userName = "root";
		String pass = "";
		
		String updateQuery = "update user set CurrentLocation = '" + CurrentLocation + "' where id = " + ID;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url,userName,pass);
			
			Statement st = con.createStatement();
			st.executeUpdate(updateQuery);
			//System.out.println("Data Entry Successfull");
			
			con.close();
		}
		catch (Exception e) 
		{
			System.out.println(e.toString());
		}
	}
    private void SelectBus_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectBus_btnActionPerformed
        
			String bID = busID_txt.getText();
			//System.out.println(bID + " -- " + busID);
			
			boolean key1 = false,key2 = false;
			
			for(int i=0;i<busIDIndex.size();i++)
			{
				if(bID.equals(busIDIndex.get(i)))
				{
					key2 = true;
				}
				else
				{
					key1 = false;
				}
			}
			
			if(key2 == true && key1 == false)
			{
                            ticketUserWindow2 tuw = new ticketUserWindow2(userName,busID,CurrentLocation,Destination,ID,Fare,Float.toString(totalDistance));
                            super.dispose();
                            tuw.setLocationRelativeTo(null);
                            tuw.setVisible(true);
                        
                        }
			else
				JOptionPane.showMessageDialog(null, "Please Enter Valid Bus ID");
			
			updateLocation();
    }//GEN-LAST:event_SelectBus_btnActionPerformed

    private void AnotherBus_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnotherBus_btnActionPerformed
        String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			try
			{
				if(busStatus.equals("Up") && busIndex.size() > 0)
				{
					for(int i = busIndex.size()-1;i>=0;i--)
					{
						
						Class.forName("com.mysql.jdbc.Driver");
						
						con = DriverManager.getConnection(url,userName,pass);
						JOptionPane.showMessageDialog(null, "Connected to DataBase");
						
						Statement st1 = con.createStatement();
						ResultSet rs1 = st1.executeQuery("select * from bus where current_location = '" + stoppages[busIndex.get(i)] + "';");
						//System.out.println(stoppages[busIndex.get(i)]);	
						
						while(rs1.next())
						{		
							busID = rs1.getString(1); //
							busLicenseID = rs1.getString(3); //
							totalSeat = rs1.getString(4); //
							emptySeat = rs1.getInt(4) - rs1.getInt(5); //
							busLocation = rs1.getString(9);					
							Fare = Float.toString(getFare(busID,totalDistance)); //
							
							busIDIndex.add(busID);
                                                        
                                                        buses += "\n\tNEAREST BUS INFORMATION "
                                                        +"\n\tBus ID No.             :   " + busID +
                                                        "\n\tBus License             :   " + busLicenseID +
                                                        "\n\tBus Total Seat          :   " + totalSeat +
                                                        "\n\tBus Empty Seat          :   " + emptySeat +
                                                        "\n\tBus Current Location  :   " + busLocation +
                                                        "\n\tFare                    : " + Fare + " Tk";
                                                        buses_txt.setText(buses);
							
                                                }
						rs1.close();
						
					}
				}
				else if(busStatus.equals("Down") && busIndex.size() > 0)
				{
					Class.forName("com.mysql.jdbc.Driver");
					
					con = DriverManager.getConnection(url,userName,pass);
					JOptionPane.showMessageDialog(null, "Connected to DataBase");
					
					Statement st1 = con.createStatement();
					ResultSet rs1 = st1.executeQuery("select * from bus where current_location = '" + reverseStoppages.get(busIndex.getLast()) + "';");
					busIndex.removeLast();
					
					while(rs1.next())
					{		
						busID = rs1.getString(1); //
						busLicenseID = rs1.getString(3); //
						totalSeat = rs1.getString(4); //
						emptySeat = rs1.getInt(4) - rs1.getInt(5); //
						busLocation = rs1.getString(9);					
						Fare = Float.toString(getFare(busID,totalDistance)); //
						
						busIDIndex.add(busID);
						
                                                buses += "\n\tNEAREST BUS INFORMATION "
                                                +"\n\tBus ID No.             :   " + busID +
                                                "\n\tBus License             :   " + busLicenseID +
                                                "\n\tBus Total Seat          :   " + totalSeat +
                                                "\n\tBus Empty Seat          :   " + emptySeat +
                                                "\n\tBus Current Location  :   " + busLocation +
                                                "\n\tFare                    : " + Fare + " Tk";
                                                buses_txt.setText(buses);
                                        }
					
					rs1.close();
					
					
				}
				
				else
				{
					JOptionPane.showMessageDialog(null, "No Available Buses Are there right now");
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
    }//GEN-LAST:event_AnotherBus_btnActionPerformed

    private void driver_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_btnActionPerformed
        String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			String BusID = busID_txt.getText();
			
			String query = "Select driver_id from bus where id = " + BusID;
			
			String driverID = null;
			
			String query1 = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				//JOptionPane.showMessageDialog(null, "Connected to DataBase");
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				rs.next();
				driverID = rs.getString(1);
				//System.out.println(driverID);
				rs.close();
				
				query1 = "SELECT b.driver_id,d.Licence_no,e.name from bus b "
						+ "left JOIN driver d on b.driver_id = d.id "
						+ "left join employee e on d.employee_id = e.id "
						+ "WHERE b.driver_id = " + driverID + " ;";
				
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery(query1);
				
				rs1.next();
					JOptionPane.showMessageDialog(null, "Driver ID : " + rs1.getString(1) + "\n"
							+ "Driver Name : " + rs1.getString(3) + "\n"
							+ "Driver Licence : " + rs1.getString(2));
				rs1.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
    }//GEN-LAST:event_driver_btnActionPerformed

    private void assistant_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assistant_btnActionPerformed
        String url = "jdbc:mysql://localhost/citybusmgnt";
			String userName = "root";
			String pass = "";
			
			String BusID = busID_txt.getText();
			
			String query = "Select driver_id from bus where id = " + BusID;
			
			String assistantID = null;
			
			String query1 = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				//JOptionPane.showMessageDialog(null, "Connected to DataBase");
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				rs.next();
				assistantID = rs.getString(1);
				//System.out.println(assistantID);
				rs.close();
				
				query1 = "SELECT b.assistant_id,e.name from bus b "
						+ "left JOIN assistant a on b.driver_id = a.id "
						+ "left join employee e on a.employee_id = e.id "
						+ "WHERE b.driver_id = " + assistantID + " ;";
				
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery(query1);
				
				rs1.next();
					JOptionPane.showMessageDialog(null, "Assistant ID : " + rs1.getString(1) + "\n"
							+ "Assistant Name : " + rs1.getString(2));
				rs1.close();
			}
			catch(Exception e)
			{
                            
                        }
			
    }//GEN-LAST:event_assistant_btnActionPerformed

    
    public void getConnection() //with data processing
	{
		String url = "jdbc:mysql://localhost/citybusmgnt";
		String userName = "root";
		String pass = "";
		String query = "select * from bus;";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url,userName,pass);
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			for(int i=stoppages.length-1;i>=0;i--)
			{
				reverseStoppages.add(stoppages[i]);
			}
			
			while(rs.next())
			{				
				if(rs.getString(7).equals(routeNumber) && rs.getString(8).equals(busStatus))
				{
										
					busLocation = rs.getString(9);					
					
					if(busStatus.equals("Up"))
					{
						for(int i=0;i<stoppages.length;i++)
						{
							if(stoppages[i].equals(busLocation) && i<=currLocIndex)
							{
								busIndex.add(i);
							}
						}
					}
					else if(busStatus.equals("Down"))
					{
						
						for(int i=0;i<reverseStoppages.size();i++)
						{
							if(reverseStoppages.get(i).equals(CurrentLocation))
							{
								currLocIndex = i;
							}
						}
						
						for(int i=0;i<reverseStoppages.size();i++)
						{
							if(reverseStoppages.get(i).equals(busLocation) && i<=currLocIndex)
							{
								busIndex.add(i);
							}
						}
						
					}
										
				}
			}
			
			rs.close();
			
			//System.out.println(busIndex.size());
			
			if(busStatus.equals("Up") && busIndex.size() > 0)
			{
				//start code for Up bus Status
				int i = busIndex.size()-1;
				//System.out.println(i);
				
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				JOptionPane.showMessageDialog(null, "Connected to DataBase");
				
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery("select * from bus where current_location = '" + stoppages[busIndex.get(i)] + "';");
				busIndex.removeLast();
				//System.out.println(stoppages[busIndex.get(i)]);	
				
				while(rs1.next())
				{		
					busID = rs1.getString(1); //
					busLicenseID = rs1.getString(3); //
					totalSeat = rs1.getString(4); //
					emptySeat = rs1.getInt(4) - rs1.getInt(5); //
					busLocation = rs1.getString(9);					
					Fare = Float.toString(getFare(busID,totalDistance)); //
					
                                        busIDIndex.add(busID);
                                        buses += "\n\tNEAREST BUS INFORMATION "
                                            +"\n\tBus ID No.             :   " + busID +
					    "\n\tBus License             :   " + busLicenseID +
                                            "\n\tBus Total Seat          :   " + totalSeat +
					    "\n\tBus Empty Seat          :   " + emptySeat +
                                            "\n\tBus Current Location  :   " + busLocation +
                                            "\n\tFare                    : " + Fare + " Tk";

				}
				rs1.close();
				// End code for Up Bus Status
				
			}
			
			//start code for down bus Status
			else if(busStatus.equals("Down") && busIndex.size() > 0)
			{
				
				
				Class.forName("com.mysql.jdbc.Driver");
				
				con = DriverManager.getConnection(url,userName,pass);
				JOptionPane.showMessageDialog(null, "Connected to DataBase");
				
				Statement st1 = con.createStatement();
				ResultSet rs1 = st1.executeQuery("select * from bus where current_location = '" + reverseStoppages.get(busIndex.getLast()) + "';");
				busIndex.removeLast();
				
				while(rs1.next())
				{		
					busID = rs1.getString(1); //
					busLicenseID = rs1.getString(3); //
					totalSeat = rs1.getString(4); //
					emptySeat = rs1.getInt(4) - rs1.getInt(5); //
					busLocation = rs1.getString(9);					
					Fare = Float.toString(getFare(busID,totalDistance)); //
					
                                        busIDIndex.add(busID);
                                        
                                        buses += "\n\tNEAREST BUS INFORMATION "
                                            +"\n\tBus ID No.             :   " + busID +
					    "\n\tBus License             :   " + busLicenseID +
                                            "\n\tBus Total Seat          :   " + totalSeat +
					    "\n\tBus Empty Seat          :   " + emptySeat +
                                            "\n\tBus Current Location  :   " + busLocation +
                                            "\n\tFare                    : " + Fare + " Tk";
//					middleTxtArea.append("\n\t\tNEAREST BUS INFORMATION ");
//					middleTxtArea.append("\n\t    Bus ID No.              \t:   " + busID);
//					middleTxtArea.append("\n\t    Bus License             \t:   " + busLicenseID);
//					middleTxtArea.append("\n\t    Bus Total Seat          \t:   " + totalSeat);
//					middleTxtArea.append("\n\t    Bus Empty Seat          \t:   " + emptySeat);
//					middleTxtArea.append("\n\t    Bus Current Location    \t:   " + busLocation);
//					middleTxtArea.append("\n	    Fare                    \t: " + Fare + " Tk");
				}
				
				rs1.close();				
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Available Buses Are there right now");
			}
			//start code for down bus Status		
						
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(searchBuses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(searchBuses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(searchBuses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(searchBuses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            //</editor-fold>

            /* Create and display the form */


            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnotherBus_btn;
    private javax.swing.JButton SelectBus_btn;
    private javax.swing.JButton assistant_btn;
    private javax.swing.JButton back_btn;
    private javax.swing.JTextField busID_txt;
    private javax.swing.JTextArea buses_txt;
    private javax.swing.JButton driver_btn;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton logout_btn;
    private javax.swing.JLabel username_txt;
    // End of variables declaration//GEN-END:variables
}
