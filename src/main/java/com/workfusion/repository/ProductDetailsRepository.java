package com.workfusion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.workfusion.beans.Product;

public class ProductDetailsRepository {
  Connection con;
  ResultSet resultset;
  PreparedStatement stmt;
  Product product;

  public List<Product> productDetailsRepo() {
    List<Product> products = new ArrayList<Product>();
    try{

      con = DBConnection.dbConnection();
      stmt = con.prepareStatement("select * from product ");

      resultset = stmt.executeQuery();
      while (resultset.next()){
        product = new Product();
        product.setProductId(resultset.getInt(1));
        product.setProductName(resultset.getString(2));
        product.setProductPrice(resultset.getFloat(3));
        product.setProductType(resultset.getString(4));
        products.add(product);
      }
    } catch (Exception e){
      e.getMessage();
    }
    return products;
  }

  public boolean checkProductExistence(int productId) {
    boolean flag = false;
    try{

      con = DBConnection.dbConnection();
      stmt = con.prepareStatement("SELECT EXISTS(SELECT * FROM product WHERE productId= ?) ");
      stmt.setInt(1, productId);
      resultset = stmt.executeQuery();
      if (resultset.next()){ flag = resultset.getInt(1) == 1 ? true : false; }
    } catch (Exception e){
      e.getMessage();
    }
    return flag;

  }

  public double checkProductAmount(int productId) {
    float productPrice = 0;
    try{

      con = DBConnection.dbConnection();
      stmt = con.prepareStatement("SELECT productPrice FROM product WHERE productId= ? ");
      stmt.setInt(1, productId);
      resultset = stmt.executeQuery();
      if (resultset.next()){ productPrice = resultset.getFloat(1); }
    } catch (Exception e){
      e.getMessage();
    }
    return productPrice;
  }

  public List<String> getProductTypes() {
    List<String> orderTypes = new ArrayList<>();
    try{
      con = DBConnection.dbConnection();

      stmt = con.prepareStatement("select distinct productType from product");
      resultset = stmt.executeQuery();
      while (resultset.next()){
        orderTypes.add(resultset.getString(1));
      }
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
    return orderTypes;
  }
}
