package com.solvd.OnlineShopping.dao.interfaces;

import java.util.List;

import com.solvd.OnlineShopping.model.Address;



public interface IAddressDAO extends IBaseDAO<Address> {

	
	List<Address> getAddressesByCity(String city);
}
