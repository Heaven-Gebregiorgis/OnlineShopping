package com.solvd.OnlineShopping.dao;


import java.util.List;

import com.solvd.OnlineShopping.model.Product;


public interface IProductDAO extends IBaseDAO<Product> {

	List<Product> getProductsByBrand(String brand);

	List<Product> getItemsInCartByCartId(int id);
	
}
