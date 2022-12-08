package com.workfusion.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	static final String DB_URL = "jdbc:mysql://localhost:3306/onlineorders?characterEncoding=utf8";
	   static final String USER = "root";
	   static final String PASS = "Nikhilsai1104";
	   static Connection con = null;
	   public static Connection dbconnection() throws ClassNotFoundException, SQLException {
		   try {
			   
		   Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager
					.getConnection(DB_URL,USER, PASS);
	   }catch(Exception e) {
		   e.getMessage();}
		return con;
		   
	   }

}
