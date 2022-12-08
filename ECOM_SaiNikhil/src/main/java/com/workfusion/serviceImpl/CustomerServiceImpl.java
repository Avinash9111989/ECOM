package com.workfusion.serviceImpl;

import java.util.Scanner;

import com.workfusion.beans.Address;
import com.workfusion.beans.Customer;
import com.workfusion.repository.CustomerRepository;
import com.workfusion.services.CustomerService;


public class CustomerServiceImpl implements CustomerService {

	Scanner scanner = new Scanner(System.in);
	public Customer c = new Customer();
	Address a = new Address();
	private CustomerRepository cr = new CustomerRepository();

	public void addNewCustomer() {
		// TODO Auto-generated method stub
		System.out.println("Enter your Name");
		c.setCustomerName(scanner.next());
		System.out.println("Enter your Mobile Number");
		c.setCustomerPhone(scanner.nextLong());
		System.out.println("Enter street name");
		a.setStreet(scanner.next());
		System.out.println("Enter city name");
		a.setCity(scanner.next());
		System.out.println("Enter pincode");
		a.setPincode(scanner.nextInt());
		System.out.println("Enter your username");
		c.setCustomerUsername(scanner.next());
		System.out.println("Enter your password");
		c.setCustomerPassword(scanner.next());
		try {
			cr.addNewCustomer(c,a);
			//customerLogin();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void updateCustomerDetails() {
		int n;
		try {
			if(customerLogin())
			{
				System.out.println("1. Change name");
				System.out.println("2. Change phone number");
				System.out.println("3. Change street name");
				System.out.println("4. Change city name");
				System.out.println("5. Change pincode");
				n = scanner.nextInt();
				System.out.println(n);
				switch(n) {
				case 1:
					System.out.println("Enter name for updation");
					c.setCustomerName(scanner.next());
					break;
				case 2:
					System.out.println("Enter phone number for updation");
					c.setCustomerPhone(scanner.nextLong());
					break;
				case 3:
					System.out.println("Enter Street name for updation");
					a.setStreet(scanner.next());
					break;
				case 4:
					System.out.println("Enter City name for updation");
					a.setCity(scanner.next());
					break;
				case 5:
					System.out.println("Enter pincode for updation");
					a.setPincode(scanner.nextInt());
					break;
				default:
					System.out.println("select correct option");
				}
				cr.updateCustomerDetails(c,n,a);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public boolean customerLogin() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Enter your username");
			c.setCustomerUsername(scanner.next());
			System.out.println("Enter your password");
			c.setCustomerPassword(scanner.next());
			cr.customerLogin(c);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
		

	}



}
