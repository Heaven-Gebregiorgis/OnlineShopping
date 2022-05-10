package com.solvd.OnlineShopping.model.enums;

public enum DeliveryStatus {

	
	DELIVERED("Delivered"),
	INTRANSIT("In transit");
	
private String value;
	
DeliveryStatus(String value) {
			this.value= value;
		}

		public String getValue() {
			return value;
		}
}
