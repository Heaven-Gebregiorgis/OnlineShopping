package com.solvd.OnlineShopping.model;

import com.solvd.OnlineShopping.model.enums.AdvertisingPreference;
import com.solvd.OnlineShopping.model.enums.Feature;
import com.solvd.OnlineShopping.model.enums.Permission;

public class Setting {

	
	private int id;
	private Feature location;
	private AdvertisingPreference advertisingPreference;
	private String language;
	private Permission cameraPermission;
	
	
	public Setting() {}
	public Setting(int id, Feature location, AdvertisingPreference advertisingPreference, String language,
	Permission cameraPermission) {
		super();
		this.id = id;
		this.location = location;
		this.advertisingPreference = advertisingPreference;
		this.language = language;
		this.cameraPermission = cameraPermission;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Feature getLocation() {
		return location;
	}


	public void setLocation(Feature location) {
		this.location = location;
	}


	public AdvertisingPreference getAdvertisingPreference() {
		return advertisingPreference;
	}


	public void setAdvertisingPreference(AdvertisingPreference advertisingPreference) {
		this.advertisingPreference = advertisingPreference;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public Permission getCameraPermission() {
		return cameraPermission;
	}


	public void setCameraPermission(Permission cameraPermission) {
		this.cameraPermission = cameraPermission;
	}
	
	
	
	
	
	
}
