package com.solvd.OnlineShopping.model;

import com.solvd.OnlineShopping.model.enums.ProductStatus;

public class Product {

	private int id;
	private String productName;
	private String brand;
	private String size;
	private ProductStatus status;
	private Category category;
	private Vendor vendor;
	
	
	
	public Product() {}
	public Product(int id, String productName, String brand, String size, ProductStatus status, Category category,
			Vendor vendor) {
		super();
		this.id = id;
		this.productName = productName;
		this.brand = brand;
		this.size = size;
		this.status = status;
		this.category = category;
		this.vendor = vendor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public ProductStatus getStatus() {
		return status;
	}
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
	
	
}
