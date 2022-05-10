package com.solvd.OnlineShopping;

import com.solvd.OnlineShopping.dao.interfaces.IAddressDAO;
import com.solvd.OnlineShopping.dao.interfaces.ICartDAO;
import com.solvd.OnlineShopping.dao.interfaces.ITransactionDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.AddressDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.CartDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.TransactionDAO;

public class Main {

	public static void main(String[] args) {
		IAddressDAO a = new AddressDAO();
		
		a.getAddressesByCity("Seattle");
	    a.getEntityById(3);
		a.removeEntity(4);
		
		ICartDAO c = new CartDAO();
		c.getEntityById(3);

		ITransactionDAO t = new TransactionDAO();
		t.removeEntity(3);
	}

}
