package com.workfusion.services;
import java.util.List;

import com.workfusion.beans.Customer;
import com.workfusion.beans.Order;

public interface OrderService {
public List<Order> displayOrders();
public void createOrderService(Customer c);
public void updateExistingOrder();
}
