package com.solvd.OnlineShopping.dao;

import java.util.List;

import com.solvd.OnlineShopping.model.Cart;
import com.solvd.OnlineShopping.model.User;

public interface ICartDAO extends IBaseDAO<Cart> {

	List<Cart> getCartsByUser(User u);
}
