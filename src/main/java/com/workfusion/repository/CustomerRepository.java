package com.workfusion.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.workfusion.beans.Customer;
import com.workfusion.serviceImpl.OrderServiceImpl;
import com.workfusion.serviceImpl.ProductDetailsImpl;

public class CustomerRepository {

  Connection con = null;
  ResultSet resultset = null;
  int customerId;
  ProductDetailsImpl prodImpl = new ProductDetailsImpl();
  OrderServiceImpl ordrImpl = new OrderServiceImpl();

<<<<<<< HEAD
	public void addNewCustomer(Customer c) throws ClassNotFoundException, SQLException, IOException {
		PreparedStatement stmt = null;
		try {
			con = DBConnection.dbConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(
					"insert into customer(custerName,phonenumber,registerdatetime) values(?,?,now())");
			stmt.setString(1, c.getCustomerName());
			stmt.setLong(2, c.getCustomerPhone());
			// stmt.setDate(3, null);
=======
  public void addNewCustomer(Customer c) throws ClassNotFoundException, SQLException {
    PreparedStatement stmt = null;
    try{
      con = DBConnection.dbConnection();
      stmt = con.prepareStatement("insert into customer(custerName,phonenumber,registerdatetime) values(?,?,now())");
      stmt.setString(1, c.getCustomerName());
      stmt.setLong(2, c.getCustomerPhone());
      // stmt.setDate(3, null);
>>>>>>> 212cff25ee7a6eb0a2606abda9937ca855588c42

      stmt.executeUpdate();
      stmt = con.prepareStatement("select customerId from customer where custerName=? and phonenumber=?");
      stmt.setString(1, c.getCustomerName());
      stmt.setLong(2, c.getCustomerPhone());

      resultset = stmt.executeQuery();
      if (resultset.next()) c.setCustomerId(resultset.getInt(1));
      stmt = con.prepareStatement("insert into Address(customerId,street,city,pincode) values(?,?,?,?)");
      stmt.setInt(1, c.getCustomerId());
      stmt.setString(2, c.getAddress().getStreet());
      stmt.setString(3, c.getAddress().getCity());
      stmt.setLong(4, c.getAddress().getPincode());
      stmt.executeUpdate();

      stmt = con
        .prepareStatement("insert into customerLogin(customerId,customeruserName,customerPassword) values(?,?,?)");
      stmt.setInt(1, c.getCustomerId());
      stmt.setString(2, c.getCustomerUsername());
      stmt.setString(3, c.getCustomerPassword());

<<<<<<< HEAD
			stmt.executeUpdate();
			con.commit();
			System.out.println(" Registration Successfull!! please Login");
=======
      stmt.executeUpdate();
      System.out.println(" Registration Successfull!! please Login");
>>>>>>> 212cff25ee7a6eb0a2606abda9937ca855588c42

    }

    catch (SQLException e){
      System.out.println(e.getMessage());

    } finally{
      con.close();
    }
  }

<<<<<<< HEAD
	public boolean customerLoginRepo(Customer c) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		ResultSet resultset = null;
		boolean loginCheck = false;
		PreparedStatement stmt;
		try {
			con = DBConnection.dbConnection();
			stmt = con.prepareStatement(
					"select customerId,count(*) from customerLogin where customeruserName=? and customerPassword=?");
			stmt.setString(1, c.getCustomerUsername());
			stmt.setString(2, c.getCustomerPassword());
			resultset = stmt.executeQuery();
			while (resultset.next()) {
				c.setCustomerId(resultset.getInt(1));
				if (resultset.getInt(2) == 0) {
					System.out.println("Login Failed Reattempt Again!!");
=======
  public boolean customerLoginRepo(Customer c) throws ClassNotFoundException, SQLException {
    ResultSet resultset = null;
    boolean loginCheck = false;
    PreparedStatement stmt;
    try{
      con = DBConnection.dbConnection();
      stmt = con.prepareStatement(
        "select customerId,count(*) from customerLogin where customeruserName=? and customerPassword=?");
      stmt.setString(1, c.getCustomerUsername());
      stmt.setString(2, c.getCustomerPassword());
      resultset = stmt.executeQuery();
      while (resultset.next()){
        c.setCustomerId(resultset.getInt(1));
        if (resultset.getInt(2) == 0){
          System.out.println("Login Failed Reattempt Again!!");
>>>>>>> 212cff25ee7a6eb0a2606abda9937ca855588c42

        } else{

<<<<<<< HEAD
					System.out.println("Login Successful!!");
					loginCheck = true;
					prodImpl.displayAllProducts(c);
				}
			}
			stmt = con.prepareStatement("update customerlogin set lastActive=now() where customerId=?");
			stmt.setInt(1, c.getCustomerId());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.getMessage();
		} finally {
			con.close();
		}
		return loginCheck;
	}
=======
          System.out.println("Login Successful!!");
          loginCheck = true;
          showProductOrderMenu(c);
        }
      }
      stmt = con.prepareStatement("update customerlogin set lastActive=now() where customerId=?");
      stmt.setInt(1, c.getCustomerId());
      stmt.executeUpdate();

    } catch (InputMismatchException e){
      System.out.println(e + "\ttry again:\n");
      showProductOrderMenu(c);
    } catch (Exception e){
      System.out.println(e.getMessage());;
    } finally{
      con.close();
    }
    return loginCheck;
  }

  private void showProductOrderMenu(Customer c) {
    System.out.println("1. view all products \n2. view order by type \nEnter your choice");
    Scanner sc = new Scanner(System.in);
    int ch = sc.nextInt();
    switch (ch) {
      case 1:
        prodImpl.displayAllProducts(c);
        break;

      case 2:
        ordrImpl.displayOrdersByType(c);
        break;

      default:
        System.out.println("invalid choice");
    }
    sc.close();
  }
>>>>>>> 212cff25ee7a6eb0a2606abda9937ca855588c42

}
