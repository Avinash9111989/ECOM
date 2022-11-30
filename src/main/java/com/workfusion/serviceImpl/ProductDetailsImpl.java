package com.workfusion.serviceImpl;


import java.util.List;

import com.workfusion.beans.Customer;
import com.workfusion.beans.Product;
import com.workfusion.repository.ProductDetailsRepository;
import com.workfusion.services.OrderService;
import com.workfusion.services.ProductDetails;

public class ProductDetailsImpl implements ProductDetails {

	ProductDetailsRepository productdetailsrepo = new ProductDetailsRepository();
    OrderService orderservice = new OrderServiceImpl();
	
	public void displayAllProducts(Customer c) {
		System.out.println("see the list of Products Below.......choose one productId to make a purchase");

		List<Product> products = productdetailsrepo.productDetailsRepo();
		System.out.println("**************************************************************");
		System.out.println("PRODUCTID\tPRODUCTNAME\tPRODUCTPRICE\tPRODUCTTYPE");
		System.out.println("**************************************************************");
		products.forEach((n) -> System.out.println(
				n.getProductId() + " " + n.getProductName() + " " + n.getProductPrice() + " " + n.getProductType()));
		System.out.println("**************************************************************");
		orderservice.createOrderService(c);
		
	}

}
