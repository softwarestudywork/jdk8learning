package com.jdk.features.beans;

public class Product {

	private Long productId;
	private String name;
	private String company;
	private Double price;
	private Integer warrantyInDays;
	
	public Product() {
	}
	
	public Product(String name, String company, Double price) {
		setName(name);
		setCompany(company);
		setPrice(price);
	}
	
	public Product(Long productId, String name, String company, Double price, Integer warrantyInDays) {
		setProductId(productId);
		setName(name);
		setCompany(company);
		setPrice(price);
		setWarrantyInDays(warrantyInDays);
	}
	
	public Product(String name, String company, Double price, Integer warrantyInDays) {
		setName(name);
		setCompany(company);
		setPrice(price);
		setWarrantyInDays(warrantyInDays);
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getWarrantyInDays() {
		return warrantyInDays;
	}

	public void setWarrantyInDays(Integer warrantyInDays) {
		this.warrantyInDays = warrantyInDays;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", company=" + company + ", price=" + price
				+ ", warrantyInDays=" + warrantyInDays + "]";
	}

}
