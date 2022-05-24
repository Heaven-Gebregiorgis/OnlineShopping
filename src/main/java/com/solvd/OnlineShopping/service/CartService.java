package com.solvd.OnlineShopping.service;

import com.solvd.OnlineShopping.dao.ICartDAO;
import com.solvd.OnlineShopping.dao.IProductDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.CartDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.ProductDAO;
import com.solvd.OnlineShopping.model.Cart;


public class CartService {
	
	private ICartDAO cartDAO = new CartDAO();
	private IProductDAO productDAO = new ProductDAO();
	
	public Cart getCartById(int id) {
		Cart result = cartDAO.getEntityById(id);
		result.setItemsInCart(productDAO.getItemsInCartByCartId(id));
		return result;
		
	}

}
