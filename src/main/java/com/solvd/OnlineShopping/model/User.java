package com.solvd.OnlineShopping.model;

import java.sql.Date;

public class User {

	private int id;
	private String lastName;
	private String firstName;
	private Date birthday;
	private String email;
	private Date registeredOn;
	private Date lastLogin;
	private CommunicationPreference communicationPreferenceId;
	private Setting settingId;
	
	
	public User() {}
	public User(int id, String lastName, String firstName, Date birthday, String email, Date registeredOn,
			Date lastLogin, CommunicationPreference communicationPreferenceId, Setting settingId) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthday = birthday;
		this.email = email;
		this.registeredOn = registeredOn;
		this.lastLogin = lastLogin;
		this.communicationPreferenceId = communicationPreferenceId;
		this.settingId = settingId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getRegisteredOn() {
		return registeredOn;
	}


	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public CommunicationPreference getCommunicationPreferenceId() {
		return communicationPreferenceId;
	}


	public void setCommunicationPreferenceId(CommunicationPreference communicationPreferenceId) {
		this.communicationPreferenceId = communicationPreferenceId;
	}


	public Setting getSettingId() {
		return settingId;
	}


	public void setSettingId(Setting settingId) {
		this.settingId = settingId;
	}
	
	
	
}
