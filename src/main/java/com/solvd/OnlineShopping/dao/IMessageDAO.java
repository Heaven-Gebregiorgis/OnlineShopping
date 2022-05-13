package com.solvd.OnlineShopping.dao;

import java.util.List;

import com.solvd.OnlineShopping.model.Message;
import com.solvd.OnlineShopping.model.User;

public interface IMessageDAO extends IBaseDAO<Message> {

	List<Message> getMessagesByUser(User u);  

}
