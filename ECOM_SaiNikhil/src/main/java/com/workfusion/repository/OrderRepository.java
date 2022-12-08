package com.workfusion.repository;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.time.LocalDate;

//import com.mysql.cj.xdevapi.Statement;
//import com.mysql.cj.xdevapi.Statement;
import com.workfusion.beans.Orders;
import com.workfusion.serviceImpl.CustomerServiceImpl;

public class OrderRepository {
	private CustomerServiceImpl csi = new CustomerServiceImpl();
	//private ProductRepository pr = new ProductRepository();
	private Orders or = new Orders();
	
	public void insertOrder() {
		try {
		Connection con = null;
		con = DBConnection.dbconnection();
		PreparedStatement stmt = con.prepareStatement("insert into orders(customerId) values ((select customerId from customerLogin where customerUserName = ?))");
		stmt.setString(1,csi.c.getCustomerUsername());
		stmt.executeUpdate();
		
		stmt = con.prepareStatement("select max(orderId) from orders where customerId = (select customerId from customerLogin where customerUserName = ?)");
		stmt.setString(1,csi.c.getCustomerUsername());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			or.setOrderId(rs.getInt(1));
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void displayOrderDetails() {
		try {
			Connection con = null;
			con = DBConnection.dbconnection();
			PreparedStatement stmt = con.prepareStatement("update orders set totalAmount = (select sum(totalAmount) from productDetails where orderId = ?), orderdate = current_timestamp,totalQuantity = (select sum(quantity) from productDetails where orderId = ?) where orderId = ?");
			stmt.setInt(1, or.getOrderId());
			stmt.setInt(3, or.getOrderId());
			stmt.setInt(2, or.getOrderId());
			stmt.executeUpdate();
			
			stmt =  con.prepareStatement("select * from orders where orderId = ?");
			stmt.setInt(1, or.getOrderId());
			ResultSet rs = stmt.executeQuery();
			System.out.println("************* Order Details ***********");
			System.out.println();
			System.out.println("OrderId" + "\t" + "TotalAmount" + "\t" + "OrderDate");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getInt(3)+"\t"+"\t"+rs.getTimestamp(4));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void cartDetails() {
		try {
			Connection con = null;
			con = DBConnection.dbconnection();
			PreparedStatement stmt =  con.prepareStatement("select * from productDetails where orderId = ?");
			stmt.setInt(1, or.getOrderId());
			ResultSet rs = stmt.executeQuery();
			System.out.println("ProductId" + "\t" + "ProductName" + "\t" + "Quantity" + "\t" + "Amount");
			while(rs.next()) {
				System.out.println(rs.getInt(2)+"\t"+"\t"+rs.getString(3)+"\t"+"\t"+rs.getInt(4)+"\t"+"\t"+rs.getInt(5));
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
