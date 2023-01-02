package com.workfusion.UI;

import java.util.Scanner;

import com.workfusion.serviceImpl.CustomerServiceImpl;
import com.workfusion.services.CustomerService;

public class EcomUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CustomerService cs = (CustomerService) new CustomerServiceImpl();
		Scanner scanner = new Scanner(System.in);
		boolean f =true;
		while (f) {
			System.out.println("1. Register New Customer");
			System.out.println("2. Login Existing Customer");
			System.out.println("3. Update Existing Customer");
			System.out.println("4. Order products");
			System.out.println("5. Previous Orders");
			System.out.println("6. Delete Orders");
			System.out.println("7. Edit orders");
			
			int option = scanner.nextInt();
			switch (option) {
			case 1:
				cs.addNewCustomer();
				break;
			case 2:
				cs.customerLogin();
				break;
			case 3:
				cs.customerLogin();
				cs.updateCustomerDetails();
				break;
			case 4:
				cs.customerLogin();
				cs.createorder();
				break;
			case 5:
				cs.customerLogin();
				cs.previousOrderDetails();
				break;
			case 6:
				cs.customerLogin();
				cs.deletorder();
				break;
			case 7:
				cs.customerLogin();
				cs.editorder();
				break;
			default:
				System.out.println("Provide the correct option");
			}
		}

	}

}
