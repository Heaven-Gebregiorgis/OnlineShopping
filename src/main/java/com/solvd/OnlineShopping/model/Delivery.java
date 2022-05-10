package com.solvd.OnlineShopping.model;

import com.solvd.OnlineShopping.model.enums.DeliveryStatus;

public class Delivery {

	
	private int id;
	private DeliveryStatus deliveryStatus;
	private User user;
	private Address address;
	
	
	
	public Delivery() {}
	public Delivery(int id, DeliveryStatus deliveryStatus, User user, Address address) {
		super();
		this.id = id;
		this.deliveryStatus = deliveryStatus;
		this.user = user;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
