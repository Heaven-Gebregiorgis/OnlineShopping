package com.solvd.OnlineShopping.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Order {

	
	private int id;
	private String orderDetail;
	private BigDecimal totalPrice;
	private Date placedAt;
	private String confirmationNumber;
	private User user;
	
	
	
	public Order() {}
	public Order(int id, String orderDetail, BigDecimal totalPrice, Date placedAt, String confirmationNumber,
			User user) {
		super();
		this.id = id;
		this.orderDetail = orderDetail;
		this.totalPrice = totalPrice;
		this.placedAt = placedAt;
		this.confirmationNumber = confirmationNumber;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getPlacedAt() {
		return placedAt;
	}
	public void setPlacedAt(Date placedAt) {
		this.placedAt = placedAt;
	}
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	public void setConfirmationNumber(String confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
