package com.solvd.OnlineShopping.dao.interfaces;


import java.util.List;

import com.solvd.OnlineShopping.model.Product;


public interface IProductDAO extends IBaseDAO<Product> {

	List<Product> getProductsByBrand(String brand);
	
}
