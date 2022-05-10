package com.solvd.OnlineShopping.dao.interfaces;

import java.sql.Date;
import java.util.List;

import com.solvd.OnlineShopping.model.User;

public interface IUserDAO extends IBaseDAO<User> {

	
	List<User> getUsersByDate(Date d);
}

