package com.solvd.OnlineShopping.dao.interfaces;

import java.util.List;

import com.solvd.OnlineShopping.model.Feedback;
import com.solvd.OnlineShopping.model.Product;


public interface IFeedbackDAO extends IBaseDAO<Feedback> {

	List<Feedback> getFeedbacksByProduct(Product p);  

}
