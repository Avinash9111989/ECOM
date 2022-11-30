package com.workfusion.beans;

public class Product {
private int productId;
private String productName;
private float productPrice;
private String productType;
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public float getProductPrice() {
	return productPrice;
}
public void setProductPrice(float productPrice) {
	this.productPrice = productPrice;
}
public String getProductType() {
	return productType;
}
public void setProductType(String productType) {
	this.productType = productType;
}
//
//public String toString() {
//	return ""+productId+"\t"+productName+"\t"+productPrice+"\t"+productType;
//}

}
