package com.workfusion.repository;

import java.sql.Connection;
//import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.workfusion.beans.Address;
import com.workfusion.beans.Customer;
import com.workfusion.beans.Orders;
import com.workfusion.beans.mtm;

public class CustomerRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/shoppe1?characterEncoding=utf8";
	static final String USER = "root";
	static final String PASS = "root";
	Scanner scanner = new Scanner(System.in);

	public void addNewCustomer(Customer c, Address a) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

			PreparedStatement stmt1 = con
					.prepareStatement("insert into customer(customerName,phonenumber) values(?,?)");
			stmt1.setString(1, c.getCustomerName());
			stmt1.setLong(2, c.getCustomerPhone());
			stmt1.executeUpdate();

			PreparedStatement stmt3 = con.prepareStatement("select customerId from customer where phonenumber=?");
			stmt3.setLong(1, c.getCustomerPhone());
			ResultSet rs3 = stmt3.executeQuery();
			rs3.next();
			c.setCustomerId(rs3.getInt(1));

			PreparedStatement stmt2 = con
					.prepareStatement("insert into address(customerId,street,city,pincode) values(?,?,?,?)");
			stmt2.setLong(1, c.getCustomerId());
			stmt2.setString(2, a.getStreet());
			stmt2.setString(3, a.getCity());
			stmt2.setLong(4, a.getPincode());
			stmt2.executeUpdate();

			PreparedStatement stmt4 = con.prepareStatement(
					"insert into CustomerLogin(customerId,customeruserName,customerPassword) values(?,?,?)");
			stmt4.setLong(1, c.getCustomerId());
			stmt4.setString(2, c.getCustomerUsername());
			stmt4.setString(3, c.getCustomerPassword());
			stmt4.executeUpdate();

			System.out.println("Registration Successfull");

			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean customerLogin(Customer c) throws ClassNotFoundException {
		boolean flag = true;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select customeruserName,customerPassword from CustomerLogin");
			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase(c.getCustomerUsername())
						&& rs.getString(2).equals(c.getCustomerPassword())) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}

			conn.close();

		} catch (Exception e) {
			e.getMessage();
		}
		return flag;

	}

	public void updateCustomerName(Customer c) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt1 = con
					.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
			stmt1.setString(1, c.getCustomerUsername());
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			c.setCustomerId(rs.getInt(1));

			PreparedStatement stmt2 = con.prepareStatement("update customer set customerName=? where customerId=?");
			stmt2.setString(1, c.getCustomerName());
			stmt2.setInt(2, c.getCustomerId());
			stmt2.executeUpdate();

			con.close();
			System.out.println("Done with the name updation");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateCustomerPhone(Customer c) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt1 = con
					.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
			stmt1.setString(1, c.getCustomerUsername());
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			c.setCustomerId(rs.getInt(1));

			PreparedStatement stmt2 = con.prepareStatement("update customer set phonenumber=? where customerId=?");
			stmt2.setLong(1, c.getCustomerPhone());
			stmt2.setInt(2, c.getCustomerId());
			stmt2.executeUpdate();

			con.close();
			System.out.println("Done with the phonenumber updation");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void updateCustomerUsername(Customer c) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt1 = con
					.prepareStatement("select customerId from CustomerLogin where customerPassword=?");
			stmt1.setString(1, c.getCustomerPassword());
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			c.setCustomerId(rs.getInt(1));

			PreparedStatement stmt2 = con
					.prepareStatement("update CustomerLogin set customeruserName=? where customerId=?");
			stmt2.setString(1, c.getCustomerUsername());
			stmt2.setInt(2, c.getCustomerId());
			stmt2.executeUpdate();

			con.close();
			System.out.println("Done with the Customerusername updation");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateCustomerPassword(Customer c) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt1 = con
					.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
			stmt1.setString(1, c.getCustomerUsername());
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			c.setCustomerId(rs.getInt(1));

			PreparedStatement stmt2 = con
					.prepareStatement("update CustomerLogin set customerPassword=? where customerId=?");
			stmt2.setString(1, c.getCustomerPassword());
			stmt2.setInt(2, c.getCustomerId());
			stmt2.executeUpdate();

			con.close();
			System.out.println("Done with the CustomerPassword updation");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateAddress(Customer c, Address a) throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement stmt1 = con
					.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
			stmt1.setString(1, c.getCustomerUsername());
			ResultSet rs = stmt1.executeQuery();
			rs.next();
			c.setCustomerId(rs.getInt(1));

			PreparedStatement stmt2 = con
					.prepareStatement("update address set street=?,city=?,pincode=? where customerId=?");
			stmt2.setString(1, a.getStreet());
			stmt2.setString(2, a.getCity());
			stmt2.setLong(3, a.getPincode());
			stmt2.executeUpdate();

			con.close();
			System.out.println("Done with the Address updation");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void createorder(Customer c, mtm m) throws ClassNotFoundException, SQLException {

		boolean flag = true;
		int count = 0;
		while (flag) {
			if (count < 1) {
				System.out.println("Select the products from the below list");
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select productId,productName,productPrice,productType from product");
				while (rs.next()) {
					int ProductId = rs.getInt(1);
					String ProductName = rs.getString(2);
					float ProductPrice = rs.getFloat(3);
					String ProductType = rs.getString(4);
					System.out.println(ProductId + "\t\t" + ProductName + "\t\t" + ProductPrice + "\t\t" + ProductType);
				}

				m.setProductId(scanner.nextInt());

				PreparedStatement stmt3 = con
						.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
				stmt3.setString(1, c.getCustomerUsername());
				ResultSet rs3 = stmt3.executeQuery();
				rs3.next();
				m.setCustomerId(rs3.getInt(1));

				PreparedStatement stmt7 = con.prepareStatement("insert into orders(customerId) values(?)");
				stmt7.setInt(1, m.getCustomerId());
				stmt7.executeUpdate();

				PreparedStatement stmt1 = con.prepareStatement("select productPrice from product where productId=?");
				stmt1.setInt(1, m.getProductId());
				ResultSet rs1 = stmt1.executeQuery();
				rs1.next();
				m.setAmount(rs1.getInt(1));

				PreparedStatement stmt6 = con.prepareStatement("select max(orderId) from orders where customerId=?");
				stmt6.setInt(1, m.getCustomerId());
				ResultSet rs6 = stmt6.executeQuery();
				rs6.next();
				m.setOrderId(rs6.getInt(1));

				PreparedStatement stmt5 = con.prepareStatement(
						"insert into mtm(orderId,customerId,productId,amount,orderDate) values(?,?,?,?,current_timestamp)");
				stmt5.setInt(1, m.getOrderId());
				stmt5.setInt(2, m.getCustomerId());
				stmt5.setInt(3, m.getProductId());
				stmt5.setFloat(4, m.getAmount());
				stmt5.executeUpdate();

				PreparedStatement stmt10 = con
						.prepareStatement("select sum(amount) from mtm group by orderId having orderId=?");
				stmt10.setInt(1, m.getOrderId());
				ResultSet rs10 = stmt10.executeQuery();
				rs10.next();
				m.setTotalAmount(rs10.getInt(1));

				con.close();
				System.out.println("Your order is been placed.");
				System.out.println("Do u want to order anything else?");
				System.out.println("If YES type true");
				System.out.println("If NO type false");
				if (scanner.nextBoolean()) {
					flag = true;
					count = count + 1;
				} else {
					flag = false;
					System.out.println(m.getTotalAmount());
				}
			} else {
				while (flag) {
					System.out.println("Select the products from the below list");
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
					Statement stmt = con.createStatement();
					ResultSet rs = stmt
							.executeQuery("select productId,productName,productPrice,productType from product");
					while (rs.next()) {
						int ProductId = rs.getInt(1);
						String ProductName = rs.getString(2);
						float ProductPrice = rs.getFloat(3);
						String ProductType = rs.getString(4);
						System.out.println(
								ProductId + "\t\t" + ProductName + "\t\t" + ProductPrice + "\t\t" + ProductType);
					}

					m.setProductId(scanner.nextInt());

					PreparedStatement stmt3 = con
							.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
					stmt3.setString(1, c.getCustomerUsername());
					ResultSet rs3 = stmt3.executeQuery();
					rs3.next();
					m.setCustomerId(rs3.getInt(1));

					PreparedStatement stmt1 = con
							.prepareStatement("select productPrice from product where productId=?");
					stmt1.setInt(1, m.getProductId());
					ResultSet rs1 = stmt1.executeQuery();
					rs1.next();
					m.setAmount(rs1.getInt(1));
					
					PreparedStatement stmt6 = con.prepareStatement(
						"select max(orderId) from orders where customerId=?");
					stmt6.setInt(1, m.getCustomerId());
					ResultSet rs6=stmt6.executeQuery();
					rs6.next();
					System.out.println(rs6.getInt(1));
					m.setOrderId(rs6.getInt(1));
					
					PreparedStatement stmt5 = con.prepareStatement(
							"insert into mtm(orderId,customerId,productId,amount,orderDate) values(?,?,?,?,current_timestamp)");
					stmt5.setInt(1, m.getOrderId());
					stmt5.setInt(2, m.getCustomerId());
					stmt5.setInt(3, m.getProductId());
					stmt5.setFloat(4, m.getAmount());
					stmt5.executeUpdate();

					PreparedStatement stmt10 = con
							.prepareStatement("select sum(amount) from mtm group by orderId having orderId=?");
					stmt10.setInt(1, m.getOrderId());
					// stmt10.setInt(1, m.getCustomerId());
					ResultSet rs10 = stmt10.executeQuery();
					rs10.next();
					m.setTotalAmount(rs10.getInt(1));

					con.close();
					System.out.println("Your order is been placed.");
					System.out.println("Do u want to order anything else?");
					System.out.println("If YES type true");
					System.out.println("If NO type false");
					if (scanner.nextBoolean()) {
						flag = true;
						count = count + 1;
					} else {
						flag = false;
						System.out.println(m.getTotalAmount());
					}
				}
			}
		}
	}

	public void previousOrderDetails(Customer c, mtm m) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

		PreparedStatement stmt = con.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
		stmt.setString(1, c.getCustomerUsername());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		m.setCustomerId(rs.getInt(1));

		PreparedStatement stmt1 = con
				.prepareStatement("select orderId,customerId,productId,amount,orderDate from mtm where customerId=?");
		stmt1.setInt(1, m.getCustomerId());
		ResultSet rs1 = stmt1.executeQuery();
		System.out.println("Here are the below products that customerId: " + m.getCustomerId() + " has ordered");
		System.out.println(
				"orderId" + "\t\t" + "customerId" + "\t" + "productId" + "\t\t" + "amount" + "\t\t" + "orderDate");
		while (rs1.next()) {
			System.out.println(rs1.getInt(1) + "\t\t" + rs1.getInt(2) + "\t\t" + rs1.getInt(3) + "\t\t"
					+ rs1.getFloat(4) + "\t\t" + rs1.getTimestamp(5));
		}
		con.close();
	}

	public void deleteorder(Customer c, mtm m) throws ClassNotFoundException, SQLException {

		boolean flag = true; 
		while (flag) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

			PreparedStatement stmt = con
					.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
			stmt.setString(1, c.getCustomerUsername());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			m.setCustomerId(rs.getInt(1));

			PreparedStatement stmt1 = con.prepareStatement(
					"select orderId,customerId,productId,amount,orderDate from mtm where customerId=?");
			stmt1.setInt(1, m.getCustomerId());
			ResultSet rs1 = stmt1.executeQuery();
			System.out.println("Here are the below products that customerId: " + m.getCustomerId() + " has ordered");
			System.out.println(
					"orderId" + "\t\t" + "customerId" + "\t" + "productId" + "\t\t" + "amount" + "\t\t" + "orderDate");
			while (rs1.next()) {
				System.out.println(rs1.getInt(1) + "\t\t" + rs1.getInt(2) + "\t\t" + rs1.getInt(3) + "\t\t"
						+ rs1.getFloat(4) + "\t\t" + rs1.getTimestamp(5));
			}

			PreparedStatement stmt2 = con
					.prepareStatement("select customerId from CustomerLogin where customeruserName=?");
			stmt2.setString(1, c.getCustomerUsername());
			ResultSet rs2 = stmt2.executeQuery();
			rs2.next();
			m.setCustomerId(rs2.getInt(1));

			System.out.println("Which products aka productId's do u wanna delete?");
			int z = scanner.nextInt();
			PreparedStatement stmt3 = con.prepareStatement("delete from mtm where productId=? and customerId=?");
			stmt3.setInt(1, z);
			stmt3.setInt(2, m.getCustomerId());
			stmt3.executeUpdate();

			System.out.println("Deleted your product with the productId  " + z);
			System.out.println("Do u want to delete any other productId's?");
			System.out.println("If YES type true");
			System.out.println("If NO type false");
			if (scanner.nextBoolean()) {
				flag = true;
			} 
			else 
			{
				flag = false;
			}

			con.close();
			
		}
		
	}
}
