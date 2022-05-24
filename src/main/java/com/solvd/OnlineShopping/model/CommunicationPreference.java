package com.solvd.OnlineShopping.model;

import com.solvd.OnlineShopping.model.enums.Email;
import com.solvd.OnlineShopping.model.enums.Permission;
import com.solvd.OnlineShopping.model.enums.Text;

public class CommunicationPreference {

	
	private int id;
	private Permission appNotification;
	private Text text;
	private Email email;
	
	public CommunicationPreference() {}
	public CommunicationPreference(int id, Permission appNotification, Text text, Email email) {
		super();
		this.id = id;
		this.appNotification = appNotification;
		this.text = text;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Permission getAppNotification() {
		return appNotification;
	}

	public void setAppNotification(Permission appNotification) {
		this.appNotification = appNotification;
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "CommunicationPreference [id=" + id + ", appNotification=" + appNotification + ", text=" + text
				+ ", email=" + email + "]";
	}
	
	
	
}
