package com.workfusion.beans;

import com.workfusion.exceptions.InvalidNameException;

public class Address {
	private String street;
	private String city;
	private long pincode;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) throws InvalidNameException {
		String pincode1=Long.toString((Long)pincode);
		if(pincode1==null || pincode1.matches("^[a-zA-Z]*$") || pincode1.length()!=6)
		{
			throw new InvalidNameException("Invalid Pincode");
		}
		else
		{
			this.pincode = pincode;	
		}
		
	}
	
	
}
