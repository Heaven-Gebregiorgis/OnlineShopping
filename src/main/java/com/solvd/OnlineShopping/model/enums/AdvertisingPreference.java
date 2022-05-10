package com.solvd.OnlineShopping.model.enums;

public enum AdvertisingPreference {

	EVERYDAY("EveryDay"),
	ONCEAWEEK("Once a week"),
	DONOTSEND("Do not send");
	
	private String value;
	
	AdvertisingPreference(String value) {
			this.value= value;
		}

		public String getValue() {
			return value;
		}
}
