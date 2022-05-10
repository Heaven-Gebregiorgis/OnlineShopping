package com.solvd.OnlineShopping.dao.jdbcmysqlimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.solvd.OnlineShopping.connectionPackage.ConnectionPool;
import com.solvd.OnlineShopping.dao.interfaces.IOrderDAO;
import com.solvd.OnlineShopping.model.Order;
import com.solvd.OnlineShopping.model.User;


public class OrderDAO implements IOrderDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);

	@Override
	public Order getEntityById(int id) {
		Order order = new Order();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Orders where id = ?");
		pr.setInt(1, id);
		
		ResultSet rs = pr.executeQuery();
		if(rs.next()) {
			order.setId(rs.getInt(1));
			order.setOrderDetail(rs.getString(2));
			order.setTotalPrice(rs.getBigDecimal(3));
			order.setPlacedAt(rs.getDate(4));
			order.setConfirmationNumber(rs.getString(5));
			order.setUser((User)rs.getObject(6));
		
		}
		}
		catch (SQLException e) {
			LOGGER.error("Failed to retrieve order");
		}
		return order;
		
	}

	@Override
	public void saveEntity(Order entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Insert into Orders values (?,?,?,?,?,?)");
			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getOrderDetail());
			pr.setBigDecimal(3, entity.getTotalPrice());
			pr.setDate(4, entity.getPlacedAt());
			pr.setString(5, entity.getConfirmationNumber());
			pr.setObject(6, entity.getUser());
		
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to save order");
		}
	}

	@Override
	public void updateEntity(Order entity) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Update Orders set 'order_detail'=?, 'total_price'=?, 'placed_at'=?, 'user_id'=?, Where ('id' = ?)");

			pr.setInt(1, entity.getId());
			pr.setString(2, entity.getOrderDetail());
			pr.setBigDecimal(3, entity.getTotalPrice());
			pr.setDate(4, entity.getPlacedAt());
			pr.setString(5, entity.getConfirmationNumber());
			pr.setObject(6, entity.getUser());
			
			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to update order");
		}
	}

	@Override
	public void removeEntity(int id) {
		try {
			PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Delete from Orders Where ('id' = ?)");
			pr.setInt(1, id);

			pr.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Failed to delete the order");
		}
		
	}

	@Override
	public List<Order> getOrdersByConfirmationNumber(String cn) {
		List<Order> list= new ArrayList<Order>();
		try {
		PreparedStatement pr = ConnectionPool.getDataSource().getConnection().prepareStatement("Select * from Orders where confirmation_number = ?");
		ResultSet rs = pr.executeQuery();
		
		while(rs.next()) {
			Order order = new Order();;
			
			order.setId(rs.getInt(1));
			order.setOrderDetail(rs.getString(2));
			order.setTotalPrice(rs.getBigDecimal(3));
			order.setPlacedAt(rs.getDate(4));
			order.setConfirmationNumber(rs.getString(5));
			order.setUser((User)rs.getObject(6));
			list.add(order);
		}
		} catch (SQLException e) {
			LOGGER.error("Failed to retrieve orders");
		}
		return list;
	}
	

}
