package com.workfusion.serviceImpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Date;

import com.workfusion.beans.Address;
import com.workfusion.beans.Customer;
import com.workfusion.beans.Orders;
import com.workfusion.beans.Products;
import com.workfusion.beans.mtm;
import com.workfusion.exceptions.InvalidNameException;
import com.workfusion.exceptions.InvalidPhoneNumberException;
import com.workfusion.repository.CustomerRepository;
import com.workfusion.services.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	Scanner scanner = new Scanner(System.in);
	Customer c = new Customer();
	Address a = new Address();
	Products p= new Products();
	Orders o=new Orders();
	mtm m=new mtm();
	Date date = new Date();
	CustomerRepository cr = new CustomerRepository();
	// CustomerService cs = (CustomerService) new CustomerServiceImpl();

	public void addNewCustomer() {
		// TODO Auto-generated method stub
		System.out.println("Enter your Name");
		try {
			c.setCustomerName(scanner.next());
		} catch (InvalidNameException e1) {
			e1.printStackTrace();
		}
		System.out.println("Enter your Mobile Number");
		try {
			c.setCustomerPhone(scanner.nextLong());
		} catch (InvalidPhoneNumberException e1) {
			e1.printStackTrace();
		}
		System.out.println("Enter your Street");
		a.setStreet(scanner.next());
		System.out.println("Enter your City");
		a.setCity(scanner.next());
		System.out.println("Enter your PinCode");
		try {
			a.setPincode(scanner.nextLong());
		} catch (InvalidNameException e1) {
			e1.printStackTrace();
		}
		System.out.println("Enter your username");
		c.setCustomerUsername(scanner.next());
		System.out.println("Enter your password");
		c.setCustomerPassword(scanner.next());

		try {
			cr.addNewCustomer(c, a);
			//customerLogin();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void customerLogin() {
		boolean flag;
		try {
			System.out.println("Enter your username");
			c.setCustomerUsername(scanner.next());
			System.out.println("Enter your password");
			c.setCustomerPassword(scanner.next());

			flag=cr.customerLogin(c);
			if(flag)
			{
				System.out.println("Login Successful!!");
			}
			else
			{
				System.out.println("Username or password is incorrect");
				customerLogin();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void updateCustomerDetails() {
		System.out.println("1. Edit CustomerName");
		System.out.println("2. Edit CustomerPhone");
		System.out.println("3. Edit CustomerUsername");
		System.out.println("4. Edit CustomerPassword");
		System.out.println("5. Edit Address");
		int option = scanner.nextInt();
		switch (option) {
		case 1:
			//customerLogin();
			System.out.println("change your CustomerName to?");
			try {
				c.setCustomerName(scanner.next());
			} catch (InvalidNameException e2) {
			}
			try {
				cr.updateCustomerName(c);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
			
		case 2:
			//customerLogin();
			System.out.println("change your CustomerPhone to?");
			try {
				c.setCustomerPhone(scanner.nextLong());
			} catch (InvalidPhoneNumberException e2) {
				e2.printStackTrace();
			}
			try {
				cr.updateCustomerPhone(c);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			break;
			
			
		case 3:
			//customerLogin();
			System.out.println("change your CustomerUsername to?");
			c.setCustomerUsername(scanner.next());
			try {
				cr.updateCustomerUsername(c);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			break;
			
			
			
		case 4:
			//customerLogin();
			System.out.println("change your CustomerPassword to?");
			c.setCustomerPassword(scanner.next());
			try {
				cr.updateCustomerPassword(c);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			break;
			
			
			
		case 5:
			//customerLogin();
			System.out.println("change your Street to?");
			a.setStreet(scanner.next());
			System.out.println("change your City to?");
			a.setCity(scanner.next());
			System.out.println("change your PinCode to?");
			try {
				a.setPincode(scanner.nextLong());
			} catch (InvalidNameException e) {
				e.printStackTrace();
			}
			try {
				cr.updateAddress(c,a);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}

			break;
		}

	}
	public void createorder() 
	{
		try 
		{
			cr.createorder(c,m);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void previousOrderDetails() 
	{
		try {
			cr.previousOrderDetails(c,m);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	public void deletorder() 
	{
		try {
			cr.deleteorder(c,m);	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	public void editorder()
	{
		System.out.println("kya karoon ismein sab kuch delete order mein kiya hua hain sivaay quantity ke");
	}
}
