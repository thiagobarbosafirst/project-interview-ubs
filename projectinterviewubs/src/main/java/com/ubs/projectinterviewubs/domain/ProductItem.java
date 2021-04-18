package com.ubs.projectinterviewubs.domain;

import java.math.BigDecimal;

import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ProductItem {
	@JsonProperty("product")
	private String product;
	@JsonProperty("quantity")
	private Integer quantity;
	@JsonProperty("price")
	private String price;
	@JsonProperty("type")
	private String type;
	@JsonProperty("industry")
	private String industry;
	@JsonProperty("origin")
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
	
	public boolean isValid() {
		return !Strings.isBlank(product) && !Strings.isBlank(type) && !Strings.isBlank(industry);
	}

	@Override
	public String toString() {
		return "ProductItem [product=" + product + ", quantity=" + quantity + ", price=" + price + ", type=" + type
				+ ", industry=" + industry + ", origin=" + origin + "]";
	}
	
}
