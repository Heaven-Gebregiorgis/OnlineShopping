package com.solvd.OnlineShopping.model;

public class Address {

	private int id;
	private int houseNumber;
	private String street;
	private String apartmentNumber;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	
	
	
	public Address() {}
	public Address(int id, int houseNumber, String street, String apartmentNumber, String city, String state,
			String postalCode, String country) {
		super();
		this.id = id;
		this.houseNumber = houseNumber;
		this.street = street;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	
	
	
}
