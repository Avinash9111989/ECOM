package com.workfusion.beans;

public class mtm {
	private int orderId;
	private int customerId;
	private int productId;
	private float amount;
	private float totalAmount;
	private java.sql.Date  orderDate;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public java.sql.Date  getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(java.sql.Date  orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
