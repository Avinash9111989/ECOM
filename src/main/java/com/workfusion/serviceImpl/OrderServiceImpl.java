package com.workfusion.serviceImpl;

import java.util.List;
import java.util.Scanner;
import com.workfusion.beans.Customer;
import com.workfusion.beans.Order;
import com.workfusion.beans.Product;
import com.workfusion.exceptions.InvalidProductIdException;
import com.workfusion.exceptions.InvalidProductTypeException;
import com.workfusion.repository.OrderRepository;
import com.workfusion.repository.ProductDetailsRepository;
import com.workfusion.services.OrderService;
import com.workfusion.validation.ProductValidation;

public class OrderServiceImpl implements OrderService {
  ProductValidation ordervalidation = new ProductValidation();
  OrderRepository orderrepo = new OrderRepository();
  Order order = new Order();
  ProductDetailsRepository prodrepo = new ProductDetailsRepository();

  @Override
  public List<Order> displayOrders() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void createOrderService(Customer c) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter ProductId to make a purchase");

    int productId = scanner.nextInt();
    if (!ordervalidation.isValidProductId(productId)){
      try{
        throw new InvalidProductIdException("Please enter valid productId");
      } catch (InvalidProductIdException e){
        System.out.println(e.getMessage());
        createOrderService(c);
      }

    } else{

      double productPrice = prodrepo.checkProductAmount(productId);
      order.setProductId(productId);
      order.setCustomerId(c.getCustomerId());
      order.setAmount(productPrice);
      orderrepo.createorderrepo(order);
    }
  }

  @Override
  public void updateExistingOrder() {
    // TODO Auto-generated method stub

  }

  public void displayOrdersByType(Customer c) {
    List<String> orderTypes = prodrepo.getProductTypes();
    if (orderTypes.size() > 0){
      System.out.println("Types of product choices: ");
      orderTypes.forEach(type -> System.out.print(type + "  "));
    } else{
      System.out.println("couldnt find any product types");
      return;
    }
    System.out.println("\n");
    System.out.print("enter the product type to categorize your orders by: ");
    Scanner sc = new Scanner(System.in);
    String enteredType = sc.next();
    try{
      if (!orderTypes.contains(enteredType.toLowerCase()))
        throw new InvalidProductTypeException("invalid type entered. enter again ");
      List<Product> productsByType = orderrepo.getOrdersByCustomerId(c.getCustomerId(), enteredType);
      if (productsByType.size() == 0) System.out.println("we couldnt find any previous orders of that type");
      else{
        System.out.println("**************************************************************");
        System.out.println("PRODUCTID\tPRODUCTNAME\tPRODUCTPRICE\tPRODUCTTYPE");
        System.out.println("**************************************************************");
        productsByType.forEach(p -> System.out.println(p.getProductId() + " \t\t  " + p.getProductName() + " \t "
          + p.getProductPrice() + " \t\t" + p.getProductType()));
        System.out.println("**************************************************************");
      }
    } catch (Exception e){
      System.out.println(e.getMessage());
      displayOrdersByType(c);
    }
  }

}
