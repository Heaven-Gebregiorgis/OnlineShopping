package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MySQLConnectionPool;
import com.solvd.OnlineShopping.dao.IFeedbackDAO;
import com.solvd.OnlineShopping.model.Feedback;
import com.solvd.OnlineShopping.model.Product;
import com.solvd.OnlineShopping.model.User;


public class FeedbackDAO implements IFeedbackDAO {

	private static final Logger LOGGER = LogManager.getLogger(FeedbackDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Feedbacks WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Feedbacks values (?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Feedbacks set 'last_name'=?, 'first_name'=?, 'birthday'=?, 'email'=?,'communication_preference_id'=?, 'setting_id'=? Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Feedbacks Where id = ?";
	private static final String GETPRODUCTSQL= "SELECT * FROM Heaven_Gebregiorgis.Feedbacks WHERE product_id=?";
	
	@Override
	public Feedback getEntityById(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		Feedback fb = new Feedback();
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		
		rs = pr.executeQuery();
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
	     finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	            }
	            catch (SQLException e) {
	                LOGGER.error("Failed to close ResultSet: " + e.getSQLState());
	            }
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
	        }
		return fb;
	}

	@Override
	public void saveEntity(Feedback entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setObject(2, entity.getUser());
			pr.setObject(3, entity.getProduct());
			pr.setInt(4, entity.getStarRate());
			pr.setString(5, entity.getReview());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save feedback");
		}
		finally {
            try {
				pool.returnConnection(con);
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
        }
	}

	@Override
	public void updateEntity(Feedback entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			
			pr.setObject(2, entity.getUser());
			pr.setObject(3, entity.getProduct());
			pr.setInt(4, entity.getStarRate());
			pr.setString(5, entity.getReview());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update feedback");
		}
		  finally {
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
		  }
		
	}

	@Override
	public void removeEntity(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(REMOVEENTITYSQL)) {
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the user");
		}
		 finally {
	            try {
					pool.returnConnection(con);
				} catch (SQLException e) {
					LOGGER.error(e.getMessage());
				}
		  }
		
	}

	@Override
	public List<Feedback> getFeedbacksByProductId(int id) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		ResultSet rs = null;
		List<Feedback> list= new ArrayList<Feedback>();
		
		try(PreparedStatement pr = con.prepareStatement(GETPRODUCTSQL)){
			
		rs = pr.executeQuery();
		
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
		finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                LOGGER.error("Failed to close ResultSet: " + e.getSQLState());
            }
            try {
				pool.returnConnection(con);
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
        }
		return list;
	}

}
