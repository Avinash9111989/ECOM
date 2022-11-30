package com.workfusion.serviceImpl;

import java.util.Scanner;

import com.workfusion.beans.Address;
import com.workfusion.beans.Customer;
import com.workfusion.repository.CustomerRepository;
import com.workfusion.services.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	Scanner scanner = new Scanner(System.in);
	Customer c = new Customer();
	CustomerRepository cr = new CustomerRepository();
    Address a = new Address();
	public void addNewCustomer() {
		// TODO Auto-generated method stub
		System.out.println("Enter your Name");
		c.setCustomerName(scanner.next());
		System.out.println("Enter your Mobile Number");
		c.setCustomerPhone(scanner.nextLong());
		System.out.println("Enter your username");
		c.setCustomerUsername(scanner.next());
		System.out.println("Enter your password");
		c.setCustomerPassword(scanner.next());
		System.out.println("Enter your street");
		a.setStreet(scanner.next());
		System.out.println("Enter your city");
		a.setCity(scanner.next());
		System.out.println("Enter your Pincode");
	    a.setPincode(scanner.nextLong());
	    c.setAddress(a);
		try {
			cr.addNewCustomer(c);
			customerLogin();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void updateCustomerDetails() {
		// TODO Auto-generated method stub

	}

	public void customerLogin() {
		// TODO Auto-generated method stub
		try {
			System.out.println("Enter your username");
			c.setCustomerUsername(scanner.next());
			System.out.println("Enter your password");
			c.setCustomerPassword(scanner.next());
			boolean flag=cr.customerLoginRepo(c);
			if(!flag) {
				customerLogin();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}



}
