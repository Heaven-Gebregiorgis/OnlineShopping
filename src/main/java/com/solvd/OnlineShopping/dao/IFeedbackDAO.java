package com.solvd.OnlineShopping.dao;

import java.util.List;

import com.solvd.OnlineShopping.model.Feedback;



public interface IFeedbackDAO extends IBaseDAO<Feedback> {

	List<Feedback> getFeedbacksByProductId(int id);  

}
