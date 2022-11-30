package com.workfusion.serviceImpl;

import java.util.List;
import java.util.Scanner;

import com.workfusion.beans.Customer;
import com.workfusion.beans.Order;
import com.workfusion.exceptions.InvalidProductIdException;
import com.workfusion.repository.OrderRepository;
import com.workfusion.repository.ProductDetailsRepository;
import com.workfusion.services.OrderService;
import com.workfusion.validation.ProductValidation;

public class OrderServiceImpl implements OrderService {
	ProductValidation ordervalidation = new ProductValidation();
	OrderRepository orderrepo = new OrderRepository();
	Order order = new Order();
    ProductDetailsRepository prodrepo = new ProductDetailsRepository();
	@Override
	public List<Order> displayOrders() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void createOrderService(Customer c) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter ProductId to make a purchase");
			
			int productId=scanner.nextInt();
			
			boolean prodstatus = ordervalidation.isValidProductId(productId);
			if (prodstatus == false) {
				throw new InvalidProductIdException("Please enter valid productId");
			} else {
				
				double productPrice=prodrepo.checkProductAmount(productId);
				order.setProductId(productId);
				order.setCustomerId(c.getCustomerId());
				order.setAmount(productPrice);
				orderrepo.createorderrepo(order);
			}
		} catch (InvalidProductIdException e) {
			e.getMessage();
		}

	}



	@Override
	public void updateExistingOrder() {
		// TODO Auto-generated method stub
		
	}

}