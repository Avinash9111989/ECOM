package com.workfusion.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import com.workfusion.beans.Customer;
import com.workfusion.serviceImpl.ProductDetailsImpl;
import com.workfusion.services.ProductDetails;

public class CustomerRepository {

	Connection con = null;
	ResultSet resultset = null;
	int customerId;
	ProductDetailsImpl prodImpl = new ProductDetailsImpl();

	public void addNewCustomer(Customer c) throws ClassNotFoundException, SQLException {
		PreparedStatement stmt = null;
		try {
			con = DBConnection.dbConnection();
			stmt = con.prepareStatement(
					"insert into customer(custerName,phonenumber,registerdatetime) values(?,?,now())");
			stmt.setString(1, c.getCustomerName());
			stmt.setLong(2, c.getCustomerPhone());
			// stmt.setDate(3, null);

			stmt.executeUpdate();
			stmt = con.prepareStatement("select customerId from customer where custerName=? and phonenumber=?");
			stmt.setString(1, c.getCustomerName());
			stmt.setLong(2, c.getCustomerPhone());

			resultset = stmt.executeQuery();
			if (resultset.next())
				c.setCustomerId(resultset.getInt(1));
			stmt = con.prepareStatement("insert into Address(customerId,street,city,pincode) values(?,?,?,?)");
			stmt.setInt(1, c.getCustomerId());
			stmt.setString(2, c.getAddress().getStreet());
			stmt.setString(3, c.getAddress().getCity());
			stmt.setLong(4, c.getAddress().getPincode());
			stmt.executeUpdate();

			stmt = con.prepareStatement(
					"insert into customerLogin(customerId,customeruserName,customerPassword) values(?,?,?)");
			stmt.setInt(1, c.getCustomerId());
			stmt.setString(2, c.getCustomerUsername());
			stmt.setString(3, c.getCustomerPassword());

			stmt.executeUpdate();
			System.out.println(" Registration Successfull!! please Login");

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			con.close();
		}
	}

	public boolean customerLoginRepo(Customer c) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ResultSet resultset = null;
		boolean loginCheck = false;
		PreparedStatement stmt;
		try {
			con = DBConnection.dbConnection();
			 stmt= con.prepareStatement(
					"select customerId,count(*) from customerLogin where customeruserName=? and customerPassword=?");
			 stmt.setString(1, c.getCustomerUsername());
			 stmt.setString(2, c.getCustomerPassword());
			resultset = stmt.executeQuery();
			while (resultset.next()) {
				c.setCustomerId(resultset.getInt(1));
				if (resultset.getInt(2) == 0) {
					System.out.println("Login Failed Reattempt Again!!");

				} else {

					System.out.println("Login Successful!!");
					loginCheck = true;
					prodImpl.displayAllProducts(c);
				}
			}
			 stmt = con
					.prepareStatement("update customerlogin set lastActive=now() where customerId=?");
			 stmt.setInt(1, c.getCustomerId());
			 stmt.executeUpdate();
			
		} catch (Exception e) {
			e.getMessage();
		} finally {
			con.close();
		}
		return loginCheck;
	}

}
