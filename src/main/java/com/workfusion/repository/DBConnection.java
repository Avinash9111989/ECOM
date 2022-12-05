package com.workfusion.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class DBConnection {

	static  String DB_URL = "";
	static  String USER = "";
	static  String PASS = "";
	static  String DRIVER ="";
	
	public static Connection dbConnection() throws IOException {
		 FileReader reader=new FileReader("D:\\Education\\JavaFS\\EclipseWorkspace\\MiscWorkspace\\ecom\\ECOM\\src\\main\\resources\\db.properties");  
		 Connection con=null;
		    Properties p=new Properties();  
		    p.load(reader);  
		      
		    DB_URL=p.getProperty("DB_URL");  
		    USER=p.getProperty("USER"); 
		    PASS=p.getProperty("PASS"); 
		    DRIVER=p.getProperty("DRIVER");
          
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.getMessage();
		}
		return con;
	}
}
