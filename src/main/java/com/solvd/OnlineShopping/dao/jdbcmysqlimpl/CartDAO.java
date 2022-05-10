package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.ICartDAO;
import com.solvd.OnlineShopping.model.Cart;
import com.solvd.OnlineShopping.model.User;


public class CartDAO implements ICartDAO {
	private static final Logger LOGGER = LogManager.getLogger(CartDAO.class);

	
	@Override
	public Cart getEntityById(int id) {
		Cart cart = new Cart();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Carts where id = ?");
		pr.setInt(1, id);
		pr.executeUpdate("Use Heaven_Gebregiorgis;");
		ResultSet rs = pr.executeQuery();
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
		return cart;
	}

	@Override
	public void saveEntity(Cart entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Carts values (?,?,?,?,?,?)");
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
	}

	@Override
	public void updateEntity(Cart entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Carts set 'created_at'=?, 'updated_at'=?, 'total_items'=?, 'total_price'=?,'user_id'=?, Where ('id' = ?)");

			
			pr.setDate(2, entity.getCreatedAt());
			pr.setDate(3, entity.getUpdatedAt());
			pr.setInt(4, entity.getTotalItems());
			pr.setBigDecimal(5, entity.getTotalPrice());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update Cart");
		}
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Carts Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate("Use Heaven_Gebregiorgis;");
			pr.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			LOGGER.error("Failed to delete the cart");
		}
	}

	@Override
	public List<Cart> getCartsByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
