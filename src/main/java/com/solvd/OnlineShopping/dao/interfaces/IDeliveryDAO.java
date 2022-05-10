package com.solvd.OnlineShopping.dao.interfaces;

import java.util.List;

import com.solvd.OnlineShopping.model.Delivery;
import com.solvd.OnlineShopping.model.User;

public interface IDeliveryDAO extends IBaseDAO<Delivery> {

	List<Delivery> getDeliveriesByUser(User u); 

}
