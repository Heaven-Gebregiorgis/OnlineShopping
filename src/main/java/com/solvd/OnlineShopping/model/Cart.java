package com.solvd.OnlineShopping.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Cart {

	
	private int id;
	private Date createdAt;
	private Date updatedAt;
	private int totalItems;
	private BigDecimal totalPrice;
	private User user;
	
	
	public Cart() {}
	public Cart(int id, Date createdAt, Date updatedAt, int totalItems, BigDecimal totalPrice, User user) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.totalItems = totalItems;
		this.totalPrice = totalPrice;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
