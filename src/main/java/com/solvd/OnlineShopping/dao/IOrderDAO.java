package com.solvd.OnlineShopping.dao;

import java.util.List;

import com.solvd.OnlineShopping.model.Order;


public interface IOrderDAO extends IBaseDAO<Order> {

	List<Order> getOrdersByConfirmationNumber(String cn);  

}
