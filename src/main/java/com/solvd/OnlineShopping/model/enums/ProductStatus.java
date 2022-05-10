package com.solvd.OnlineShopping.model.enums;

public enum ProductStatus {

	INSTOCK("In stock"),
	SOLDOUT("Sold out");
	
	private String value;
	
	ProductStatus(String value) {
			this.value= value;
		}

		public String getValue() {
			return value;
		}
}
