package com.workfusion.services;

import java.sql.SQLException;

import com.workfusion.exceptions.InvalidPhoneNumberException;

public interface CustomerService {
public void addNewCustomer() throws InvalidPhoneNumberException, ClassNotFoundException, SQLException;
public void updateCustomerDetails();
public void customerLogin();
}