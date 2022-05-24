package com.solvd.OnlineShopping.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vendor {

	@JsonProperty
	private int id;
	@JsonProperty
	private String vendorName;
	@JsonProperty
	private String contactName;
	@JsonProperty
	private String email;
	@JsonProperty
	private String phone;
	@JsonProperty("establishedDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MM/dd/yyyy")
	private Date estDate;
	@JsonProperty
	private Address address;
	
	
	public Vendor() {}
	public Vendor(int id, String vendorName, String contactName, String email, String phone, Address address) {
		super();
		this.id = id;
		this.vendorName = vendorName;
		this.contactName = contactName;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	@JsonProperty
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vandorName) {
		this.vendorName = vandorName;
	}
	
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Date getEstDate() {
		return estDate;
	}
	public void setEstDate(Date estDate) {
		this.estDate = estDate;
	}
	@Override
	public String toString() {
		return "Vendor [id=" + id + ", vendorName=" + vendorName + ", contactName=" + contactName + ", email=" + email
				+ ", phone=" + phone + ", estDate=" + estDate + ", address=" + address + "]";
	}
	
	
	
}
