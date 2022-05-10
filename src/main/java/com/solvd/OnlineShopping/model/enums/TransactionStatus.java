package com.solvd.OnlineShopping.model.enums;

public enum TransactionStatus {

	
	POSTED("Posted"),
	PENDING("Pending");
	
	private String value;
	
	TransactionStatus(String value) {
			this.value= value;
		}

		public String getValue() {
			return value;
		}
}
