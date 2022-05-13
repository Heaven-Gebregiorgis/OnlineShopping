package com.solvd.OnlineShopping.dao;

import java.util.Date;
import java.util.List;

import com.solvd.OnlineShopping.model.User;

public interface IUserDAO extends IBaseDAO<User> {

	
	List<User> getUsersByDate(Date d);
}

