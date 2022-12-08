package com.workfusion.serviceImpl;

import java.util.Scanner;

//import com.workfusion.beans.Customer;
import com.workfusion.beans.Products;
import com.workfusion.repository.ProductRepository;
//import com.workfusion.serviceImpl.CustomerServiceImpl;
import com.workfusion.services.ProductServices;

public class ProductServiceImpl implements ProductServices {

	Scanner sc = new Scanner(System.in);
	Products p = new Products();
	private ProductRepository pr = new ProductRepository();
	
	String f = null;
	public void addProduct() {
		System.out.println("Enter productId : ");
		p.setProductId(sc.nextInt());
			if(p.getProductId() == 0) {
				System.out.println("Do you want to proceed add products to cart and place order y/n");
				f = sc.next();
				if(f.equals("y")) {
				System.out.println("Products added successfully");
				}
				else {
					System.out.println("1. add products to cart\n2. edit products in cart");
					int ae = sc.nextInt();
					if(ae == 1) {
						displayCategories();
						addProduct();
					}
					else {
						editProductDetails();
						addProduct();
					}
				}
			}
			else
			{
				System.out.println("Enter quantity :");
				p.setQuantity(sc.nextInt());
				pr.addProduct(p,pr.choice);
				addProduct();
			}
	}
	
	public void editProductDetails() {
		pr.displayCart();
		System.out.println("1. To delete complete product\n2. To change the quantity of the products in the cart");
		int i = sc.nextInt();
		if(i == 1) {
			System.out.println("Enter the productId to delete the product");
			p.setOrderId(sc.nextInt());
			pr.deleteProduct(i);
		}
		else {
			System.out.println("Enter the productId and qunatity to update");
			System.out.println();
			System.out.println("Enter productId");
			p.setOrderId(sc.nextInt());
			System.out.println("Enter the quantity");
			p.setQuantity(sc.nextInt());
			pr.deleteProduct(i);
		}
	}

	
	public void displayCategories() {
		 pr.displayCategories();
	}
	
	public void displayProducts() {
		pr.displayProducts(pr.choice);
		
	}
}
