package com.workfusion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.workfusion.beans.Order;
import com.workfusion.beans.Product;

public class OrderRepository {
  public void createorderrepo(Order order) {
    PreparedStatement stmt;
    Connection con;
    try{
      con = DBConnection.dbConnection();
      stmt = con.prepareStatement("insert into orders(customerId,productId,amount,orderDate) values(?,?,?,now())");
      stmt.setInt(1, order.getCustomerId());
      stmt.setInt(2, order.getProductId());
      stmt.setDouble(3, order.getAmount());

      stmt.executeUpdate();
      System.out.println("\"Order Placed Successfully!! Thank You!\"");
    } catch (Exception e){
      e.getMessage();
    }
  }

  public List<Product> getOrdersByCustomerId(int id, String type) {
    List<Product> ordersByType = new ArrayList<>();
    String query = "select o.orderId, o.customerId, p.productId, o.amount, p.productName, p.productPrice, p.productType, o.orderDate from product as p inner join orders as o on p.productId = o.productId having p.productType='"
      + type + "' and o.customerId= " + id + " order by o.orderId";

    PreparedStatement stmt;
    Connection con;
    try{
      con = DBConnection.dbConnection();
      stmt = con.prepareStatement(query);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()){
        Product p = new Product();
        p.setProductId(rs.getInt("productId"));
        p.setProductName(rs.getString("productName"));
        p.setProductPrice(rs.getInt("productPrice"));
        p.setProductType(rs.getString("productType").toLowerCase());
        ordersByType.add(p);
      }
    } catch (Exception e){
      System.out.println(e.getMessage());;
    }
    return ordersByType;
  }

}
