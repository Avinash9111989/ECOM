package com.workfusion.beans;

import com.workfusion.exceptions.InvalidNameException;
import com.workfusion.exceptions.InvalidPhoneNumberException;

public class Customer {

	private int customerId;
	private String customerName;
	private long customerPhone;
	private String customerUsername;
	private String customerPassword;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) throws InvalidNameException {
		if(customerName==null || !customerName.matches("^[a-zA-Z]*$"))
		{
			throw new InvalidNameException("Invalid Name");
		}
		else
		{
			this.customerName = customerName;	
		}
		
	}
	public long getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(long customerPhone) throws InvalidPhoneNumberException {
		String customerPhone1=Long.toString((Long) customerPhone);
		Long pnum1=customerPhone;
		if((customerPhone1.length()<10) || (customerPhone1.length()>10) || (int)customerPhone1.charAt(0)<54)
		{
			throw new InvalidPhoneNumberException("Invalid PhoneNumber");
		}
		else
		{
			this.customerPhone = customerPhone;	
		}
		
	}
	public String getCustomerUsername() {
		return customerUsername;
	}
	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	
	
}
