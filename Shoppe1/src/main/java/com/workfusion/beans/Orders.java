package com.workfusion.beans;

import java.time.LocalDateTime;
import java.util.Date;

public class Orders {
	
	
	private int orderId;
	private int customerId;
	private float total;
	
	

	
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
	
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	
	
	
}
