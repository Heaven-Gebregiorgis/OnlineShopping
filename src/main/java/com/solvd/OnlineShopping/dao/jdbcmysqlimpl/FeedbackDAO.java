package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.IFeedbackDAO;
import com.solvd.OnlineShopping.model.Feedback;
import com.solvd.OnlineShopping.model.Product;
import com.solvd.OnlineShopping.model.User;


public class FeedbackDAO implements IFeedbackDAO {

	private static final Logger LOGGER = LogManager.getLogger(FeedbackDAO.class);
	
	@Override
	public Feedback getEntityById(int id) {
		Feedback fb = new Feedback();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Feedbacks where id = ?");
		pr.setInt(1, id);
		
		ResultSet rs = pr.executeQuery();
		if(rs.next()) {
			fb.setId(rs.getInt(1));
			fb.setUser((User)rs.getObject(2));
			fb.setProduct((Product)rs.getObject(3));
			fb.setStarRate(rs.getInt(4));
			fb.setReview((rs.getString(5)));
			
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve feedback");
		}
		return fb;
	}

	@Override
	public void saveEntity(Feedback entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Feedbacks values (?,?,?,?,?)");
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getUser());
			pr.setObject(3, entity.getProduct());
			pr.setInt(4, entity.getStarRate());
			pr.setString(5, entity.getReview());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save feedback");
		}
	}

	@Override
	public void updateEntity(Feedback entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Users set 'last_name'=?, 'first_name'=?, 'birthday'=?, 'email'=?,'communication_preference_id'=?, 'setting_id'=? Where ('id' = ?)");

			
			pr.setObject(2, entity.getUser());
			pr.setObject(3, entity.getProduct());
			pr.setInt(4, entity.getStarRate());
			pr.setString(5, entity.getReview());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update feedback");
		}
		
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Feedbacks Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the user");
		}
		
	}

	@Override
	public List<Feedback> getFeedbacksByProduct(Product p) {
		List<Feedback> list= new ArrayList<Feedback>();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Feedbacks where product_id = ?");
		ResultSet rs = pr.executeQuery();
		
		Feedback fb = new Feedback();
		while(rs.next()) {
			
			fb.setId(rs.getInt(1));
			fb.setUser((User)rs.getObject(2));
			fb.setProduct((Product)rs.getObject(3));
			fb.setStarRate(rs.getInt(4));
			fb.setReview((rs.getString(5)));
			list.add(fb);
		}
		} catch (SQLException e) {
			LOGGER.error("Failed to retrieve feedbacks");
		}
		return list;
	}

}
