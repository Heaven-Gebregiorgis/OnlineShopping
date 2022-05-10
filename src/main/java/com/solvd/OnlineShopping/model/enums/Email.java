package com.solvd.OnlineShopping.model.enums;

public enum Email {

	
	YES("Yes"),
	NO("No");
	
    private String value;
	

    Email(String value) {
		this.value= value;
	}

	public String getValue() {
		return value;
	}
}
