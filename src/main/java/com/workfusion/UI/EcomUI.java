package com.workfusion.UI;

import java.util.Scanner;
import com.workfusion.serviceImpl.CustomerServiceImpl;
import com.workfusion.services.CustomerService;

public class EcomUI {

  public static void main(String[] args) {
    // TODO Auto-generated method stub

    CustomerService cs = new CustomerServiceImpl();
    Scanner scanner = new Scanner(System.in);
    boolean flag = false;
    while (!flag){
      System.out.println("1. Register New Customer");
      System.out.println("2. Login Existing Customer");
      System.out.println("3. Update Existing Customer");
      int option = scanner.nextInt();
      switch (option) {
        case 1:
          cs.addNewCustomer();
          flag = true;
          break;

        case 2:
          cs.customerLogin();
          flag = true;
          break;

        case 3:
          cs.updateCustomerDetails();
          flag = true;
          break;

        default:
          System.out.println("Please provide correct option");
      }
    }

  }

}
