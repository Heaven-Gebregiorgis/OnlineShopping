package com.solvd.OnlineShopping.model;

import java.sql.Date;

public class Message {

	private int id;
	private String email;
	private String subject;
	private String bodyDetail;
	private Date messageDate;
	private User user;
	
	public Message(){}
	public Message(int id, String email, String subject, String bodyDetail, Date messageDate, User user) {
		super();
		this.id = id;
		this.email = email;
		this.subject = subject;
		this.bodyDetail = bodyDetail;
		this.messageDate = messageDate;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBodyDetail() {
		return bodyDetail;
	}
	public void setBodyDetail(String bodyDetail) {
		this.bodyDetail = bodyDetail;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
