package com.workfusion.UI;

import java.util.Scanner;

//import com.workfusion.beans.Customer;
//import com.workfusion.repository.ProductRepository;
import com.workfusion.serviceImpl.*;
import com.workfusion.services.*;

public class EcomUI {

	public static void main(String[] args) {

		CustomerService cs = new CustomerServiceImpl();
		ProductServices ps = new ProductServiceImpl();
		OrderServices os = new OrderServiceImpl();
		
		Scanner scanner = new Scanner(System.in);
		
		boolean f=true;
		
		while (f) {
			
			System.out.println("1. Register new customer");
			System.out.println("2. Login existing customer");
			System.out.println("3. Update existing customer");
			System.out.println("4. Display products");
			System.out.println("5. Exit");
			
			int option = scanner.nextInt();
			
			switch (option) {
			
			case 1:
				cs.addNewCustomer();
				cs.customerLogin();
				ps.displayCategories();
				System.out.println("Details registered successfully");
				f=false;
				break;
				
			case 2:
				cs.customerLogin();
				f=false;
				break;
				
			case 3:
				cs.customerLogin();
				cs.updateCustomerDetails();
				
				System.out.println("Details updated successfully");
				
				f=false;
				
				break;
				
			case 4:
				ps.displayCategories();
				System.out.println("Do you want to place order y/n");
				
				String s = scanner.next();
				if(s.equals("y")) {
					
					System.out.println("For new customer press 1 \nFor existing customer press 2");
					int i = scanner.nextInt();
					boolean ad=true;
					while(ad) {			
						switch(i) {
							case 1:
								cs.addNewCustomer();
								os.orderInsert();
								System.out.println("Enter productId and quantity to complete press 0");
								ps.displayProducts();
								ps.addProduct();
								os.viewOrder();
								ad=false;
								f=false;
								break;
							case 2:
								cs.customerLogin();
								os.orderInsert();
								System.out.println("Enter productId and quantity to complete press 0");
								ps.displayProducts();
								ps.addProduct();
								os.viewOrder();
								ad=false;
								f=false;
								break;
							default:
								System.out.println("Please enter valid number");
								ad=true;
						}
					}
				}
				else
				{
					System.out.println("Thanks for using our ECOM service Please visit again");
					f=false;
				}
				break;
				
			case 5:
				System.out.println("why exit buy some products paisal evvariki oorike raavu but vachinapudu konukovali");
				f=false;
				break;
				
			default:
				System.out.println("Please provide correct option");
			}
		}

		scanner.close();
	}
}

