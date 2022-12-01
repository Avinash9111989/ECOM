package com.workfusion.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
  static final String DB_URL = "jdbc:mysql://localhost:3306/ecom?characterEncoding=utf8";
  static final String USER = "root";
  static final String PASS = "admin";

  public static Connection dbConnection() {
    Connection con = null;
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (Exception e){
      System.out.println(e.getMessage());;
    }
    return con;
  }
}
