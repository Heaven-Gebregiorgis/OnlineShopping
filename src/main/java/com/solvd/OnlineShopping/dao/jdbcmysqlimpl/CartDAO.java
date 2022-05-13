package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connection.MySQLConnectionPool;
import com.solvd.OnlineShopping.dao.ICartDAO;
import com.solvd.OnlineShopping.model.Cart;
import com.solvd.OnlineShopping.model.User;


public class CartDAO implements ICartDAO {
	private static final Logger LOGGER = LogManager.getLogger(CartDAO.class);
	private static final String GETENTITYSQL= "SELECT * FROM Heaven_Gebregiorgis.Carts WHERE id=?";
	private static final String SAVEENTITYSQL= "Insert into Heaven_Gebregiorgis.Carts values (?,?,?,?,?,?)";
	private static final String UPDATEENTITYSQL= "Update Heaven_Gebregiorgis.Carts set 'created_at'=?, 'updated_at'=?, 'total_items'=?, 'total_price'=?,'user_id'=?, Where ('id' = ?)";
	private static final String REMOVEENTITYSQL= "Delete from Heaven_Gebregiorgis.Carts Where id = ?";

	
	@Override
	public Cart getEntityById(int id) {
		
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		Cart cart = new Cart();
		ResultSet rs = null;
		try (PreparedStatement pr = con.prepareStatement(GETENTITYSQL)){
		pr.setInt(1, id);
		pr.executeUpdate("Use Heaven_Gebregiorgis;");
		rs = pr.executeQuery();
		if(rs.next()) {
			cart.setId(rs.getInt(1));
			cart.setCreatedAt(rs.getDate(2));
			cart.setUpdatedAt(rs.getDate(3));
			cart.setTotalItems(rs.getInt(4));
			cart.setTotalPrice(rs.getBigDecimal(5));
			cart.setUser((User)rs.getObject(6));
			
		}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			LOGGER.error("Failed to retrieve cart");
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
			return cart;
	}

	@Override
	public void saveEntity(Cart entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		
		try(PreparedStatement pr = con.prepareStatement(SAVEENTITYSQL);) {
			pr.setInt(1, entity.getId());
			pr.setDate(2, entity.getCreatedAt());
			pr.setDate(3, entity.getUpdatedAt());
			pr.setInt(4, entity.getTotalItems());
			pr.setBigDecimal(5, entity.getTotalPrice());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save Cart");
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
	public void updateEntity(Cart entity) {
		MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
		Connection con = pool.getAvailableConnection();
		try(PreparedStatement pr = con.prepareStatement(UPDATEENTITYSQL)) {

			
			pr.setDate(2, entity.getCreatedAt());
			pr.setDate(3, entity.getUpdatedAt());
			pr.setInt(4, entity.getTotalItems());
			pr.setBigDecimal(5, entity.getTotalPrice());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update Cart");
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

			pr.executeUpdate("Use Heaven_Gebregiorgis;");
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the cart: " + e.getMessage());
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
	public List<Cart> getCartsByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
