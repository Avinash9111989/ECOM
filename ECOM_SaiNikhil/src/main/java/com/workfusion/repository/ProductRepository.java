package com.workfusion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
//import com.mysql.cj.protocol.Resultset;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;

//import org.junit.runner.JUnitCommandLineParseResult.CommandLineParserError;

import java.util.Scanner;

//import com.mysql.cj.protocol.Resultset;
//import com.workfusion.beans.Customer;
//import com.workfusion.beans.Customer;
import com.workfusion.beans.Products;
import com.workfusion.serviceImpl.CustomerServiceImpl;

public class ProductRepository {
	Scanner scanner = new Scanner(System.in);
	HashMap <Integer,String> map = new HashMap<>();
	public int choice = 0, s = 0;
	
	private CustomerServiceImpl csi = new CustomerServiceImpl();
	Products p = new Products();
	
	public void displayCategories() {
		try {	
			int i = 1;
			Connection con = null;
			con=DBConnection.dbconnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select distinct productType from product");
			System.out.println("******** Displaying Categories avaliable ********");
			while(rs.next()) {
				System.out.println(i + "." + " " + rs.getString(1));
				map.put(i,rs.getString(1));
				i++;
			}
			System.out.println("*************************");
			System.out.println("Select category");
			choice=scanner.nextInt();
			for(Entry<Integer, String> m:map.entrySet()) {
				if(choice == m.getKey()) {
					displayProducts(choice);
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void addProduct(Products p,int choice) {
		try {
			Connection conn = null;
			conn = DBConnection.dbconnection();
			PreparedStatement stmt = conn.prepareStatement("select customerId from customerLogin where customerUserName = ?");
			stmt.setString(1,csi.c.getCustomerUsername());
			ResultSet rs1 = stmt.executeQuery();
			while(rs1.next()) {
				s = rs1.getInt(1);
			}
			stmt = conn.prepareStatement("insert into productDetails(productId,productName,orderId,quantity,totalamount) values (?,(select productName from product where productId = ?),(select max(orderId) from orders where customerId = ?),?,(? * (select productPrice from product where productId = ?)))");
			stmt.setInt(1, p.getProductId());
			stmt.setInt(2, p.getProductId());
			stmt.setInt(3,s);
			stmt.setInt(4, p.getQuantity());
			stmt.setInt(5, p.getQuantity());
			stmt.setInt(6,p.getProductId());
			stmt.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void displayProducts(int choice) {
		try {
			Connection conn = null;
			conn=DBConnection.dbconnection();
			PreparedStatement stmt1 = conn.prepareStatement("select productId,productName,productPrice from product where productType = ?");
			stmt1.setString(1,map.get(choice));
			ResultSet rs1 = stmt1.executeQuery();
			System.out.println("******** Displaying Products avaliable ********");
			System.out.println("ProductId"+"\t"+"ProductName"+"\t"+"ProductPrice");
			while(rs1.next()) {
				System.out.println(rs1.getInt(1)+"\t"+"\t"+rs1.getString(2)+"\t"+"\t"+rs1.getString(3));
			}
		System.out.println("*********************************************");
	
		}
		catch(Exception e) {
		System.out.println(e.getMessage());
		}
	}
	
	public void displayCart() {
		try {
			Connection con = null;
			con = DBConnection.dbconnection();
			PreparedStatement stmt = con.prepareStatement("select productId,productName,quantity from productDetails where orderId = (select orderId from orders where customerId = ?)");
			stmt.setInt(1,s);
			ResultSet rs = stmt.executeQuery();
			System.out.println("******** Displaying Products in cart ********");
			System.out.println("ProductId"+"\t"+"ProductName"+"\t"+"ProductPrice");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+"\t"+rs.getString(2)+"\t"+"\t"+rs.getInt(3));			
			}
		System.out.println("*********************************************");
		con.close();	
		}
		catch(Exception e) {
			System.out.println(e.getMessage()); 
		}
	}
	
	public void deleteProduct(int i) {
		try {
			Connection con = null;
			con = DBConnection.dbconnection();
			if(i == 1) {
				PreparedStatement stmt = con.prepareStatement("delete from productDetails where productId = ?");
				stmt.setInt(1,p.getOrderId());
				stmt.executeUpdate();
			}
			else{
				PreparedStatement stmt = con.prepareStatement("update productDetails set quantity = ? where productId = ?");
				stmt.setInt(1, p.getQuantity());
				stmt.setInt(2, p.getProductId());
				stmt.executeUpdate();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
