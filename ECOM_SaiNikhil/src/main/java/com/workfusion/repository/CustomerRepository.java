
package com.workfusion.repository;

import java.sql.Connection;

//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.workfusion.exceptions.*;
import com.workfusion.serviceImpl.CustomerServiceImpl;
import com.workfusion.beans.Address;
import com.workfusion.beans.Customer;

public class CustomerRepository {
	   int flag = 0;

	public void addNewCustomer(Customer c, Address a) throws ClassNotFoundException {
		try {
			Connection con = null;
			con=DBConnection.dbconnection();
			PreparedStatement stmtc = con.prepareStatement("insert into customer(customerName,phonenumber) values(?,?)");
			stmtc.setString(1, c.getCustomerName());
			stmtc.setLong(2, c.getCustomerPhone());
			stmtc.executeUpdate();
			
			stmtc = con.prepareStatement("insert into address(customerId,street,city,pincode) values ((select customerId from customer where customerName = ?),?,?,?)");
			stmtc.setString(1,c.getCustomerName());
			stmtc.setString(2,a.getStreet());
			stmtc.setString(3, a.getCity());
			stmtc.setInt(4, a.getPincode());
			stmtc.executeUpdate();
			
			stmtc = con.prepareStatement("insert into customerLogin(customerId, customerUserName, customerPassword) values((select customerId from customer where customerName =?),?,?)");
			stmtc.setString(1,c.getCustomerName());
			stmtc.setString(2, c.getCustomerUsername());
			stmtc.setString(3, c.getCustomerPassword());
			stmtc.executeUpdate();
			
			System.out.println(" Registration Successfull!! ");
            
			con.close();

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());

		}
	}

	public void customerLogin(Customer c) throws ClassNotFoundException, SQLException, InvalidLoginException {
		CustomerServiceImpl cri = new CustomerServiceImpl();
		try {
		  Connection conn = null;
		  conn=DBConnection.dbconnection();
		         Statement stmt = conn.createStatement();
		         ResultSet rs = stmt.executeQuery("select customerUserName,customerPassword from customerLogin");		      
		         while(rs.next()){
		           if(rs.getString(1).equalsIgnoreCase(c.getCustomerUsername()) && rs.getString(2).equals(c.getCustomerPassword())){
		        	   flag =1;
		        	   break;
		           }
		         }
		         
		         if (flag ==1) { 
		        	 flag = 0;
		        	 System.out.println("Login successful");	 
		         }
		         
		         else {
		        	 //flag = 0;
		        	 throw new InvalidLoginException("Login failed please enter the details again");
		         }
		         conn.close();
		      }
		
		catch(ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		catch(InvalidLoginException e) {
			System.out.println(e.getMessage());
			cri.customerLogin();
			
		}
	}
	public void updateCustomerDetails(Customer c,int n,Address a) {
		boolean f = false;
		try {
			Connection con = null;
			con = DBConnection.dbconnection();
			PreparedStatement stmt = con.prepareStatement("select customerId from customerLogin where customerUserName = ?");
			stmt.setString(1, c.getCustomerUsername());
			ResultSet rs= stmt.executeQuery();
			f=rs.next();
			while(f) {
				//System.out.println("entered while loop");
				switch(n) {
				case 1:
					PreparedStatement stmt1 = con.prepareStatement("update customer set customerName = ? where customerId = ?");
					stmt1.setString(1, c.getCustomerName());
					stmt1.setInt(2,rs.getInt(1));
					stmt1.executeUpdate();
					System.out.println("executed successful");
					f=false;
					break;
				case 2:
					PreparedStatement stmt2 = con.prepareStatement("update customer set phoneNumber = ? where customerId = ?");
					stmt2.setLong(1, c.getCustomerPhone());
					stmt2.setInt(2,rs.getInt(1));
					stmt2.executeUpdate();
					f=false;
					break;
				case 3:
					PreparedStatement stmt3 = con.prepareStatement("update address set street = ? where customerId = ?");
					stmt3.setString(1, a.getStreet());
					stmt3.setInt(2,rs.getInt(1));
					stmt3.executeUpdate();
					f=false;
					break;
				case 4:
					PreparedStatement stmt4 = con.prepareStatement("update address set city = ? where customerId = ?");
					stmt4.setString(1, a.getCity());
					stmt4.setInt(2,rs.getInt(1));
					stmt4.executeUpdate();
					f=false;
					break;
				case 5:
					PreparedStatement stmt5 = con.prepareStatement("update address set pincode = ? where customerId = ?");
					stmt5.setInt(1, a.getPincode());
					stmt5.setInt(2,rs.getInt(1));
					stmt5.executeUpdate();
					f=false;
					break;
				default:
					f=false;
				}
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
