package com.solvd.OnlineShopping.model;


import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.solvd.OnlineShopping.fileparser.DateAdapter;


@XmlRootElement(name = "user")
public class User {

	
	private int id;
	private String lastName;
	private String firstName;
	private Date birthday;
	private String email;
	private Date registeredOn;
	private Date lastLogin;
	private CommunicationPreference communicationPreference;
	private Setting setting;
	private List<Order> orders;
	
	
	public User() {}
	public User(int id, String lastName, String firstName, Date birthday, String email, Date registeredOn,
			Date lastLogin, CommunicationPreference communicationPreference, Setting setting) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthday = birthday;
		this.email = email;
		this.registeredOn = registeredOn;
		this.lastLogin = lastLogin;
		this.communicationPreference = communicationPreference;
		this.setting = setting;
	}

	@XmlAttribute(name = "id")
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "lname")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "fname")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement(name = "bday")
	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@XmlElement(name = "email")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@XmlJavaTypeAdapter(DateAdapter.class)
	@XmlElement(name = "registeredon")
	public Date getRegisteredOn() {
		return registeredOn;
	}


	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}

	@XmlElement(name = "lastlogin")
	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@XmlElement(name = "compreference")
	public CommunicationPreference getCommunicationPreference() {
		return communicationPreference;
	}


	public void setCommunicationPreference(CommunicationPreference communicationPreference) {
		this.communicationPreference = communicationPreference;
	}

	@XmlElement(name = "setting")
	public Setting getSetting() {
		return setting;
	}


	public void setSetting(Setting setting) {
		this.setting = setting;
	}
	@XmlElementWrapper(name = "orders")
	@XmlElement(name = "order", type = Order.class)
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", birthday=" + birthday
				+ ", email=" + email + ", registeredOn=" + registeredOn + ", lastLogin=" + lastLogin
				+ ", communicationPreference=" + communicationPreference + ", setting=" + setting + ", orders=" + orders
				+ "]";
	}
	
	
	
}
