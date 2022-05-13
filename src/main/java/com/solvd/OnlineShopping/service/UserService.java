package com.solvd.OnlineShopping.service;


import com.solvd.OnlineShopping.dao.IUserDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.OrderDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.UserDAO;
import com.solvd.OnlineShopping.model.User;

public class UserService {
	
	private IUserDAO userDAO = new UserDAO();
	private OrderDAO orderDAO = new OrderDAO();
		

	public User getUserById(int id) {
		User result = userDAO.getEntityById(id);
		result.setOrders(orderDAO.getOrdersByUserId(id));
		return result;
		
	}
}
