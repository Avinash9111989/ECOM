package com.workfusion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.workfusion.beans.Order;

public class OrderRepository {
public void createorderrepo(Order order) {
	PreparedStatement stmt;
	Connection con;
	try {
		con = DBConnection.dbConnection();
		stmt = con.prepareStatement(
				"insert into orders(customerId,productId,amount,orderDate) values(?,?,?,now())");
		stmt.setInt(1, order.getCustomerId());
		stmt.setInt(2, order.getProductId());
		stmt.setDouble(3, order.getAmount());

		stmt.executeUpdate();
		System.out.println("\"Order Placed Successfully!! Thank You!\"");
	}catch(Exception e) {
		e.getMessage();
	}
}
}
