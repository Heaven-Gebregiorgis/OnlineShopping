package com.solvd.OnlineShopping.service;

import com.solvd.OnlineShopping.dao.IFeedbackDAO;
import com.solvd.OnlineShopping.dao.IProductDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.FeedbackDAO;
import com.solvd.OnlineShopping.dao.jdbcmysqlimpl.ProductDAO;
import com.solvd.OnlineShopping.model.Product;


public class ProductService {
	private IProductDAO productDAO = new ProductDAO();
	private IFeedbackDAO feedbackDAO = new FeedbackDAO();
		

	public Product getProductById(int id) {
		Product result = productDAO.getEntityById(id);
		result.setFeedbacks(feedbackDAO.getFeedbacksByProductId(id));
		return result;
		
	}

}
