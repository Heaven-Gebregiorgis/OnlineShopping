package com.solvd.OnlineShopping.model.enums;

public enum Department {

	CLOTHES("Clothes"),
	SHOES("Shoes"),
	HOME("Home"),
	ELECTRONICS("Electronics");
	
	private String value;
	
	Department(String value) {
			this.value= value;
		}

		public String getValue() {
			return value;
		}
}
