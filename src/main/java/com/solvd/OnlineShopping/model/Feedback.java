package com.solvd.OnlineShopping.model;

public class Feedback {

	
	private int id;
	private User user;
	private Product product;
	private int starRate;
	private String review;
	
	
	public Feedback() {}
	public Feedback(int id, User user, Product product, int starRate, String review) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.starRate = starRate;
		this.review = review;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getStarRate() {
		return starRate;
	}
	public void setStarRate(int starRate) {
		this.starRate = starRate;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
}
