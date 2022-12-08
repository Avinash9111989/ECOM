package com.workfusion.serviceImpl;

import com.workfusion.repository.OrderRepository;
//import com.workfusion.repository.ProductRepository;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;

//import com.workfusion.repository.DBConnection;
import com.workfusion.services.OrderServices;

public class OrderServiceImpl implements OrderServices {
	private OrderRepository or = new OrderRepository();
	//private ProductRepository pr = new ProductRepository();
	
	public void orderInsert(){
		or.insertOrder();
	}

	public void viewOrder() {
		or.displayOrderDetails();
		System.out.println();
		System.out.println("***********cart details*************");
		or.cartDetails();
		
	}

	public void deleteOrder() {
		
	}

}
