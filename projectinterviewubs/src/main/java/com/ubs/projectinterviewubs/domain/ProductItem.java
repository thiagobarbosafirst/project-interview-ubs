package com.ubs.projectinterviewubs.domain;

import java.math.BigDecimal;

public class ProductItem {
	
	private String product;
	
	private Integer quantity;
	
	private String price;
	
	private String type;
	
	private String industry;
	
	private String origin;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public String toString() {
		return "ProductItem [product=" + product + ", quantity=" + quantity + ", price=" + price + ", type=" + type
				+ ", industry=" + industry + ", origin=" + origin + "]";
	} 
	
}
