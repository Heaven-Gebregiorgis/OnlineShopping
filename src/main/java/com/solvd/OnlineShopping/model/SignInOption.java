package com.solvd.OnlineShopping.model;

import com.solvd.OnlineShopping.model.enums.Feature;

public class SignInOption {

	private int id;
	private String loginId;
	private String password;
	private Feature faceId;
	private User user;
	
	
	public SignInOption() {}
	public SignInOption(int id, String loginId, String password, Feature faceId, User user) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.password = password;
		this.faceId = faceId;
		this.user = user;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Feature getFaceId() {
		return faceId;
	}


	public void setFaceId(Feature faceId) {
		this.faceId = faceId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
